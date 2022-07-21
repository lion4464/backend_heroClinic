package com.example.demo.user;

import com.example.demo.company.CompanyEntity;
import com.example.demo.company.CompanyService;
import com.example.demo.configuration.TokenUtil;
import com.example.demo.configuration.UserDetailsImpl;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.exceptions.UserAlreadyExistException;
import com.example.demo.exceptions.UserNotActivatedException;
import com.example.demo.role.RoleEntity;
import com.example.demo.role.RoleService;
import com.example.demo.role.Rolemapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service

public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final CompanyService companyService;
    private final Rolemapper rolemapper;
    private final PasswordEncoder passwordEncoder;
    private final TokenUtil tokenUtil;
    @Value("${app.jjwt.tokenType}")
    private String tokenType;
    @Value("${app.jjwt.access_expiration}")
    private String accessExpirationTime;
    @Value("${app.jjwt.refresh_expiration}")
    private String refreshExpirationTime;

    private static final Logger logger = LogManager.getLogger();

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, CompanyService companyService, Rolemapper rolemapper, PasswordEncoder passwordEncoder, TokenUtil tokenUtil) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.companyService = companyService;
        this.rolemapper = rolemapper;
        this.passwordEncoder = passwordEncoder;
        this.tokenUtil = tokenUtil;
    }

    private AuthResponse createAuthResponse(UserEntity userEntity) {
        UserDetailsImpl userDetails = generateUserDetails(userEntity);
        String accessToken = tokenUtil.generateAccessToken(userDetails);
        String refreshToken = tokenUtil.generateRefreshToken(userDetails);
        return new AuthResponse(accessToken, refreshToken, tokenType, Long.valueOf(accessExpirationTime), Long.valueOf(refreshExpirationTime));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            logger.info("User not found exception does occure");
            throw new UsernameNotFoundException("This username is not in our db :/");
        } else {
            logger.info("User found {}", username);
        }
        UserEntity userEntity = user.get();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        userEntity.getRole().forEach(roleEntity -> {
            authorities.add(new SimpleGrantedAuthority(roleEntity.getName()));
        });

        return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
    }


    private UserEntity saveUser(String username, String firstName, String lastName, String password, String phone, DataStatusEnum active, String role, UUID companyId) throws NoSuchAlgorithmException {
        logger.info("User save {}", username);
        UserEntity userEntity = new UserEntity();
        CompanyEntity companyEntity = companyService.get(companyId);
        userEntity.setUsername(username);
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);

        userEntity.setPassword(passwordEncoder.encode(password));
        userEntity.setHashPassword(generateMD5Hash(password));
        RoleEntity roleEntity = roleService.findByName(role);
        if (roleEntity == null)
            throw new DataNotFoundException("This role not found between our roles");
        List<RoleEntity> roleList = new ArrayList<>();
        roleList.add(roleEntity);
        userEntity.setRole(roleList);
        userEntity.setPhone(phone);
        userEntity.setStatus(active);
        userEntity.setCompany(companyEntity);
        return userRepository.save(userEntity);
    }

    private String generateMD5Hash(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String md5Hex = DatatypeConverter.printHexBinary(digest).toUpperCase(Locale.ROOT);
        return md5Hex;
    }


    private void addRoleToUser(String userName, String roleName) {
        logger.info("Saving new role({}) for user({})", roleName, userName);
        Optional<UserEntity> optional = userRepository.findByUsername(userName);
        if (optional.isEmpty())
            throw new DataNotFoundException("This user not found between our users");
        UserEntity user = optional.get();
        RoleEntity role = roleService.findByName(roleName);
        if (role == null)
            throw new DataNotFoundException("This role not found between our roles");
        user.getRole().add(role);
        userRepository.save(user);
    }


    @Override
    public List<HashMap<String, Object>> getAllUsers() {
        logger.info("Fetching all users");
        List<HashMap<String, Object>> result = new ArrayList<>();
        for (UserEntity user : userRepository.findAll()) {
            HashMap<String, Object> res = new HashMap<>();
            res.put("id", user.getId());
            res.put("username", user.getUsername());
            res.put("status", user.getStatus());
            result.add(res);
        }
        return result;
    }

    @Override
    public UserEntity getUser(String username) throws DataNotFoundException {
        logger.info(" user found at {}", username);
        if (userRepository.findByUsername(username).isEmpty())
            throw new DataNotFoundException("User is not in our db");
        return userRepository.findByUsername(username).get();
    }

    @Override
    public AuthResponse signIn(SignInRequest signInRequest) {
        Optional<UserEntity> optional = userRepository.findByUsername(signInRequest.getUsername());
        if (optional.isPresent()) {
            if (!optional.get().getStatus().equals(DataStatusEnum.ACTIVE)) {
                throw new UserNotActivatedException(signInRequest.getUsername());
            }
            if (!passwordEncoder.matches(signInRequest.getPassword(), optional.get().getPassword())) {
                throw new BadCredentialsException(signInRequest.getUsername());
            }
            return createAuthResponse(optional.get());
        }

        throw new BadCredentialsException(signInRequest.getUsername());
    }

    @Override
    public AuthResponse signUp(SignUpRequest signUpRequest) throws NoSuchAlgorithmException {
        Optional<UserEntity> optional = userRepository.findByUsername(signUpRequest.getUsername());
        if (optional.isPresent()) {
            throw new UserAlreadyExistException("name_is_already_exists");
        }
        UserEntity userEntity = saveUser(signUpRequest.getUsername(), signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getPassword(), signUpRequest.getPhone(), signUpRequest.getStatus(), signUpRequest.getRoleName(), signUpRequest.getCompanyId());

        logger.info("new user is created in our table the username is {}", signUpRequest.getUsername());
        return createAuthResponse(userEntity);

    }

    @Override
    public UserEntity findById(UUID userId) {
        UserEntity user = userRepository.findById(userId).get();
        if (!user.getStatus().equals(DataStatusEnum.ACTIVE))
            throw new DataNotFoundException("User not found in our Db");
        return user;
    }

    @Override
    public void logout(TokensRequest tokensRequest) {
        tokenUtil.revokeTokens(tokensRequest.getAccessToken(), tokensRequest.getRefreshToken());
    }


    private UserEntity findOldPassword(String username, String oldPassword) throws NoSuchAlgorithmException {
        UserEntity userEntity = getUser(username);
        String oldPassswordFromRequest = generateMD5Hash(oldPassword);
        if (!userEntity.getHashPassword().equals(oldPassswordFromRequest))
            throw new DataNotFoundException("Password is not match old password or  it is not gave");
        return userEntity;
    }

    @Override
    public HashMap<String, Object> changePassword(String username, PasswordRequest request) throws NoSuchAlgorithmException {
        UserEntity entity = findOldPassword(username, request.getOldPassword());
        entity.setPassword(passwordEncoder.encode(request.getNewPassword()));
        entity.setHashPassword(generateMD5Hash(request.getNewPassword()));
        UserEntity res = userRepository.save(entity);
        HashMap<String, Object> result = new HashMap<>();
        result.put("username", res.getUsername());
        result.put("status", res.getStatus());
        result.put("role", res.getRole().get(0).getName());
        return result;
    }

    @Override
    public UserEntity changeStatus(UUID id, UserRequest request) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty())
            throw new DataNotFoundException("User is not in our db");
        UserEntity entity = userEntity.get();
        entity.setStatus(request.getStatus());
        return userRepository.save(entity);
    }


    private UserDetailsImpl generateUserDetails(UserEntity userEntity) {
        return new UserDetailsImpl(userEntity);
    }
}

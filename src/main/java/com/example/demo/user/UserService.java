package com.example.demo.user;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.exceptions.UserAlreadyExistException;
import com.example.demo.role.RoleEntity;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public interface UserService {
UserEntity getUser(String username) throws DataNotFoundException;
List<HashMap<String,Object>> getAllUsers(UserEntity user, DataStatusEnum status);
AuthResponse signIn(SignInRequest signInRequest);
AuthResponse signUp(SignUpRequest signUpRequest) throws NoSuchAlgorithmException, UserAlreadyExistException, DataNotFoundException;
UserEntity findById(UUID userId) throws DataNotFoundException;

    void logout(TokensRequest tokensRequest);


    HashMap<String,Object> changePassword(String username, PasswordRequest request) throws NoSuchAlgorithmException, DataNotFoundException;

    UserEntity changeStatus(UUID id, UserRequest request) throws DataNotFoundException;
}

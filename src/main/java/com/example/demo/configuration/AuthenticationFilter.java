package com.example.demo.configuration;

import com.example.demo.revoke_access_token.RevokeAccessTokenService;
import com.example.demo.user.UserEntity;
import com.example.demo.user.UserService;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.UUID;
@Component
public class AuthenticationFilter extends OncePerRequestFilter {
    private final TokenUtil tokenUtil;
    private final RevokeAccessTokenService revokeAccessTokenService;
    private final UserService userService;

    public AuthenticationFilter(TokenUtil tokenUtil, RevokeAccessTokenService revokeAccessTokenService, UserService userService) {
        this.tokenUtil = tokenUtil;
        this.revokeAccessTokenService = revokeAccessTokenService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!request.getMethod().equalsIgnoreCase("OPTIONS")) {
            String headerName = "Authorization";
            final String authHeader = request.getHeader(headerName);
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                 if (revokeAccessTokenService.isRevoked(token))
                    throw new AccessDeniedException("Token expired!");

                 else if (tokenUtil.validateToken(token)){
                     UUID userId = UUID.fromString(tokenUtil.getUsernameFromToken(token));
                     UserEntity userEntity;
                     try {
                         userEntity = userService.findById(userId);

                     } catch (Exception e) {
                         throw new AccessDeniedException("User not found");
                     }
                     UserDetailsImpl userDetails = new UserDetailsImpl(userEntity);
                     UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                     authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                     SecurityContextHolder.getContext().setAuthentication(authentication);
                }
                 else {

                 }
            }
            filterChain.doFilter(request, response);
        }
    }
}

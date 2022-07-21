package com.example.demo.configuration;

import com.example.demo.user.DataStatusEnum;
import com.example.demo.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
@ToString
public class UserDetailsImpl implements UserDetails {
    private final UserEntity userEntity;
    private final List<SimpleGrantedAuthority> authorities;

    public UserDetailsImpl() {
        this.userEntity = new UserEntity();
        authorities = new ArrayList<>();
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public UserDetailsImpl(UserEntity userEntity) {
        this.userEntity = userEntity;
        authorities = new ArrayList<>();
        userEntity.getRole().forEach(role->{
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities != null ? authorities : new ArrayList<>();
    }


    @JsonIgnore
    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }
    public UUID getId() {
        return this.userEntity.getId();
    }
    public DataStatusEnum getStatus(){ return this.userEntity.getStatus();}
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

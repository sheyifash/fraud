package com.example.frauddetection.Entity;

import com.example.frauddetection.Enum.AuthStatus;
import com.example.frauddetection.Enum.Role;
import jakarta.persistence.*;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
public class UserModel implements UserDetails {
    @Id
    private String merchantID;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String username;
    @Column
    private String merchantName;
    @Column
    private String password;
    @Column
    private String mobile;
    @Column
    private String adminId;//optional
    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column
    private String message;
    @Column
    private AuthStatus status;
    @Column
    private String token;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }
}

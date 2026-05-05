package com.example.frauddetection.Service;

import com.example.frauddetection.Entity.UserModel;
import com.example.frauddetection.Repo.UserRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MerchantDetailService implements UserDetailsService {
    private final UserRepo userRepository;

    public MerchantDetailService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        System.out.println("user in detail service" + user);
        System.out.println(user.getRole().name());
        // Map your UserModel to Spring Security's UserDetails
//        return org.springframework.security.core.userdetails.User
//                .withUsername(user.getUsername())
//                .password(user.getPassword())
//                .authorities(new SimpleGrantedAuthority(user.getRole().name()))
//                .build();

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("ADMIN")
                .build();
    }

}

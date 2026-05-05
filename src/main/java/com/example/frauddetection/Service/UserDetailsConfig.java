//package com.example.frauddetection.Service;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.userdetails.*;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
//public class UserDetailsConfig {
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails admin = User.withUsername("admin")
//                .password("{noop}adminpassword")  // {noop} = no encoding, for testing only
//                .roles("ADMIN")                   // -> authority: ROLE_ADMIN
//                .build();
//
//        // You can add more users if needed
//        return new InMemoryUserDetailsManager(admin);
//    }
//}
package com.fillexc.service;

import com.fillexc.domain.CustomUserDeatils;
import com.fillexc.domain.User;
import com.fillexc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepo.findByUsername(s);

        optionalUser
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        return optionalUser
                .map(CustomUserDeatils::new).get();
    }
}

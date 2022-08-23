package com.clinic.dentalclinic.auth.service;

import com.clinic.dentalclinic.auth.model.User;
import com.clinic.dentalclinic.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsCustomService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with email %s not found".formatted(email)));
        return user;
    }


}

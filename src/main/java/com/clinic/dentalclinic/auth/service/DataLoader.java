package com.clinic.dentalclinic.auth.service;

import com.clinic.dentalclinic.auth.model.User;
import com.clinic.dentalclinic.auth.model.UserRole;
import com.clinic.dentalclinic.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {
    private final UserRepository userRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pass1 = passwordEncoder.encode("password");
        String pass2 = passwordEncoder.encode("password2");

        User user1 = new User("foo", "foo1", "foo@email.com", pass1, UserRole.ROLE_ADMIN);
        User user2 = new User("foo2", "foo2", "foo2@email.com", pass2, UserRole.ROLE_USER);

        userRepository.save(user1);
        userRepository.save(user2);
    }
}

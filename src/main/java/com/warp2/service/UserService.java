package com.warp2.service;

import com.warp2.mail.MailSender;
import com.warp2.model.Status;
import com.warp2.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import com.warp2.model.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final MailSender mailSender;

    public UserService(UserRepository userRepository, MailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User xUser = userRepository.findByEmail(email);

        if (xUser == null) {
            throw new UsernameNotFoundException("Unknown user: " + email);
        }

        UserDetails user;
        user = org.springframework.security.core.userdetails.User.builder()
                .username(xUser.getEmail())
                .password(xUser.getPassword())
                .roles(xUser.getRole())
                .build();
        return user;
    }

    public boolean addUser(User user) {

        User user1 = userRepository.findByEmail(user.getEmail());

        if (user1 != null) {
            return false;
        }
        Random random = new Random();
        long x = random.nextLong();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encPass = encoder.encode(user.getPassword());
        user.setName(user.getName());
        user.setPassword(encPass);
        user.setEmail(user.getEmail());
        user.setStatus(Status.NOT_ACTIVE);
        user.setRole("USER");
        user.setId(String.valueOf(x));

        user.setActivationCode(UUID.randomUUID().toString());
        userRepository.save(user);

        if (!StringUtils.isEmpty(user.getEmail())) {

            String message = String.format("Hello, %s! \n" + "Welcome! Please, click this link: http://localhost:8080/activate/%s",
                    user.getName(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Activation Code", message);
        }
        return true;
    }

    public boolean activateUser(String code) {

        User xUser = userRepository.findByActivationCode(code);

        if (xUser == null) {
            return false;
        }

        xUser.setActivationCode(null);
        xUser.setStatus(Status.ACTIVE);
        userRepository.save(xUser);
        return true;
    }
}

package com.bawag.cms.jwt.security.service;

import com.bawag.cms.auth.model.User;
import com.bawag.cms.auth.repository.UsersRepository;
import com.bawag.cms.auth.service.UserDetailsImpl;
import com.bawag.cms.jwt.security.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("jwtUserDetailsService")
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository userRepository;

    //Working code for JWT token
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userEmail);

        if (null != user) {
            System.out.println("loadUserByUsername method username  :" + userEmail);
            return JwtUserFactory.create(user);
        } else {

            Optional<User> optionalUser = userRepository.findByUserEmail(userEmail);
            Optional.ofNullable(optionalUser).orElseThrow(() -> new UsernameNotFoundException("Username Not Found"));
            System.out.println("loadUserByUsername method optionalUser  :" + optionalUser.get());
            return optionalUser.map(UserDetailsImpl::new).get(); }

    }
}
package com.pfe.bookstore.services;


import com.pfe.bookstore.entities.User;
import com.pfe.bookstore.repositories.IUserRepository;
import com.pfe.bookstore.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findUserByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
       return new MyUserPrincipal(user);
    }
}
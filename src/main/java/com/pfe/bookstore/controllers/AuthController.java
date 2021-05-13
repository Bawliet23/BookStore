package com.pfe.bookstore.controllers;

import com.pfe.bookstore.DTO.*;
import com.pfe.bookstore.entities.Auteur;
import com.pfe.bookstore.entities.Client;
import com.pfe.bookstore.security.MyUserPrincipal;
import com.pfe.bookstore.services.IAuteurService;
import com.pfe.bookstore.services.IClientService;
import com.pfe.bookstore.utils.JwtTokenUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;
    @Autowired
    private IClientService clientService;
    @Autowired
    private IAuteurService auteurService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        System.out.println(authenticationRequest.getUsername());
        System.out.println(passwordEncoder.encode(authenticationRequest.getPassword()));
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final MyUserPrincipal userDetails = (MyUserPrincipal) userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token,modelMapper.map(userDetails.getUser(), UserDTO.class)));
    }
    @PostMapping(value = "/signUp",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signUp(@RequestBody RegisterDTO user) throws Exception {
        if (user.getRole().equals("client")){
        Client client = modelMapper.map(user,Client.class);
            Client client1=clientService.addClient(client);
        UserDetails userDetails=new MyUserPrincipal(client1);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token,modelMapper.map(client1, ClientDTO.class)));
        }else if(user.getRole().equals("auteur")){
            Auteur auteur = modelMapper.map(user,Auteur.class);
            Auteur auteur1 = auteurService.addAuteur(auteur);
            UserDetails userDetails=new MyUserPrincipal(auteur1);
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token,modelMapper.map(auteur1, AuteurDTO.class)));
        }
        return ResponseEntity.badRequest().body("Bad Credentials ");
    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

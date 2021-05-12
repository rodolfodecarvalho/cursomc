package com.rodolfoguerra.cursomc.controllers;

import com.rodolfoguerra.cursomc.dto.EmailDTO;
import com.rodolfoguerra.cursomc.security.JWTUtil;
import com.rodolfoguerra.cursomc.security.UserSS;
import com.rodolfoguerra.cursomc.services.AuthService;
import com.rodolfoguerra.cursomc.services.UserService;
import com.rodolfoguerra.cursomc.services.exceptions.AuthorizationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final JWTUtil jwtUtil;

    private final AuthService service;

    public AuthController(JWTUtil jwtUtil, AuthService service) {
        this.jwtUtil = jwtUtil;
        this.service = service;
    }

    @PostMapping(value = "/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSS user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Acesso negado");
        }
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/forgot")
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {
        service.sendNewPassword(objDto.getEmail());
        return ResponseEntity.noContent().build();
    }
}
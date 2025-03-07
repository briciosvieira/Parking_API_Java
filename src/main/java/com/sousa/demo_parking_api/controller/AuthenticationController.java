package com.sousa.demo_parking_api.controller;

import com.sousa.demo_parking_api.jwt.JwtToken;
import com.sousa.demo_parking_api.jwt.JwtUserDetailsService;
import com.sousa.demo_parking_api.web.Dto.userDto.UserLoginDto;
import com.sousa.demo_parking_api.web.exception.ErrorMessageException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

    private final JwtUserDetailsService jwtUserDetailsService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<Object> authenticate(@RequestBody @Valid UserLoginDto dto, HttpServletRequest request){

        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
            authenticationManager.authenticate(authenticationToken);

            JwtToken token = jwtUserDetailsService.getTokenAuthenticated(dto.getUsername());
            return  ResponseEntity.ok(token);

        } catch (AuthenticationException e) {

        }
        return ResponseEntity.badRequest().body(new ErrorMessageException(request, HttpStatus.BAD_REQUEST, "Credenciais inválidas"));
    }
}

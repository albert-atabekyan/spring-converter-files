package com.spring.jwt.controller;

import com.spring.jwt.model.JwtRequest;
import com.spring.jwt.model.JwtResponse;
import com.spring.jwt.model.UserModel;
import com.spring.jwt.service.UserService;
import com.spring.jwt.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Objects;

@RestController
public class HomeController {

    private JWTUtility jwtUtility;
    private AuthenticationManager authenticationManager;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setJwtUtility(JWTUtility jwtUtility) {
        this.jwtUtility = jwtUtility;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @GetMapping("/")
    public String home() {
        return "Welcome!!";
    }

    @PostMapping("/convertFile/{extension}")
    public ResponseEntity<?> convertPDFFileEndpoint(@PathVariable String extension,
                                                    Principal principal,
                                                    @RequestParam("file") MultipartFile file
    ) {
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, Object> multiValueMap =
                new LinkedMultiValueMap<>();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        multiValueMap.add("file", file.getResource());

        HttpEntity<MultiValueMap<String, Object>> multiValueMapHttpEntity
                = new HttpEntity<>(multiValueMap, headers);

        ResponseEntity<byte[]> responseEntity =
                restTemplate.postForEntity("http://localhost:8081/api/v1/convertFile/" + extension
                        , multiValueMapHttpEntity
                        , byte[].class);

        if(responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST) {
            return ResponseEntity.badRequest().body(responseEntity.getBody());
        } else if(responseEntity.getStatusCode() == HttpStatus.OK) {
            ResponseEntity<String> kafkaResponse =
                restTemplate.postForEntity("http://localhost:8080/api/v1/sendMessage",
                        principal.getName()
                                + " "
                                + extension,
                        String.class);

            if(kafkaResponse.getStatusCode() == HttpStatus.OK) {
                if(extension.equals("pdf")) {
                    return ResponseEntity.ok()
                            .contentType(MediaType.APPLICATION_PDF)
                            .body(responseEntity.getBody());
                }
            }
        }

        return ResponseEntity.badRequest().body("Unexpected error");
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );

            System.out.println(jwtRequest.getUsername());
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = userService.loadUserByUsername(jwtRequest.getUsername());

        System.out.println("controller: " + userDetails);

        final String token =
                jwtUtility.generateToken(userDetails);

        System.out.println(token);

        return  new JwtResponse(token);
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel) {
        userService.registerUser(userModel);

        return "Success";
    }
}

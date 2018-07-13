package com.delicacycomics.delicacy.rest;

import com.delicacycomics.delicacy.dto.request.LoginSignInDto;
import com.delicacycomics.delicacy.dto.request.UserRegisterDto;
import com.delicacycomics.delicacy.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(path = "/login", method = POST)
    public ResponseEntity login(@RequestBody LoginSignInDto loginSignInDto,
                                HttpServletResponse response) {
        authenticationService.authenticate(loginSignInDto.getLogin(),
                loginSignInDto.getPassword(), response);
        return new ResponseEntity(OK);
    }

    @RequestMapping(path = "/login/admin", method = POST)
    public ResponseEntity loginAdmin(@RequestBody LoginSignInDto loginSignInDto,
                                     HttpServletResponse response) {
        authenticationService.authenticateAdmin(loginSignInDto.getLogin(),
                loginSignInDto.getPassword(), response);
        return new ResponseEntity(OK);
    }

    @RequestMapping(path = "/register", method = POST)
    public ResponseEntity register(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        authenticationService.register(userRegisterDto);
        return new ResponseEntity(OK);
    }

}

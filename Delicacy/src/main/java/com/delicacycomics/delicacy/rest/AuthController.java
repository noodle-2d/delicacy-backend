package com.delicacycomics.delicacy.rest;

import com.delicacycomics.delicacy.dto.request.LoginRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @RequestMapping(path = "/login", method = POST)
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto) {
        // todo
        return new ResponseEntity(OK);
    }

}

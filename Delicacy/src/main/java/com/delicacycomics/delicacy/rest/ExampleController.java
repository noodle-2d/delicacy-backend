package com.delicacycomics.delicacy.rest;

import com.delicacycomics.delicacy.dto.request.ExampleRequestDto;
import com.delicacycomics.delicacy.dto.response.ExampleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/example")
public class ExampleController {

    @RequestMapping(path = "/{id}", method = GET)
    public ExampleResponseDto getExampleById(@PathVariable("id") Long id) {
        System.out.println("getExampleById");
        System.out.println("id = " + id);
        return new ExampleResponseDto();
    }

    @RequestMapping(method = GET)
    public Page<ExampleResponseDto> getExamplesPage(@RequestParam(name = "stringQueryParam", required = false) String stringQueryParam,
                                                    @RequestParam(name = "longArrayQueryParam", required = false) long[] longArrayQueryParam,
                                                    Pageable pageable) {
        System.out.println("getExamplesPage");
        System.out.println("stringQueryParam = " + stringQueryParam);
        System.out.println("longArrayQueryParam = " + Arrays.toString(longArrayQueryParam));
        System.out.println("pageable = " + pageable);
        return new PageImpl<>(new ArrayList<>(), pageable, 0);
    }

    @RequestMapping(method = POST)
    public ExampleResponseDto createExample(@RequestBody ExampleRequestDto exampleRequestDto) {
        System.out.println("createExample");
        System.out.println("exampleRequestDto = " + exampleRequestDto);
        return new ExampleResponseDto();
    }

    @RequestMapping(path = "/additional", method = POST)
    public ResponseEntity someAdditionalPostRequest(@RequestBody ExampleRequestDto exampleRequestDto,
                                                    HttpServletRequest httpServletRequest) {
        System.out.println("someAdditionalPostRequest");
        System.out.println("exampleRequestDto = " + exampleRequestDto);
        System.out.println("IP address = " + httpServletRequest.getRemoteAddr());
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = PUT)
    public ResponseEntity updateExampleById(@PathVariable("id") Long id,
                                            @RequestBody ExampleRequestDto exampleRequestDto) {
        System.out.println("updateExampleById");
        System.out.println("id = " + id);
        System.out.println("exampleRequestDto = " + exampleRequestDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = DELETE)
    public ResponseEntity deleteExampleById(@PathVariable("id") Long id) {
        System.out.println("deleteExampleById");
        System.out.println("id = " + id);
        return new ResponseEntity(HttpStatus.OK);
    }

}

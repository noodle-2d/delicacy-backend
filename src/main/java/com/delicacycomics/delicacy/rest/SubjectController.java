package com.delicacycomics.delicacy.rest;

import com.delicacycomics.delicacy.dto.response.SubjectDto;
import com.delicacycomics.delicacy.entity.Subject;
import com.delicacycomics.delicacy.service.SubjectService;
import com.delicacycomics.delicacy.util.PageableUtils;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private MapperFacade mapperFacade;


    @RequestMapping(method = GET)
    public Page<SubjectDto> getSubjects(@RequestParam(value = "query", required = false) String query,
                                        @RequestParam(value = "type") String subjectType,
                                        Pageable pageable) { // Why do we need this method?
        Page<Subject> orders = new PageImpl<>(Collections.emptyList(), pageable, 0);
        System.out.println("query = " + query);
        System.out.println("type = " + subjectType);
        System.out.println("Some subject lists");
        return PageableUtils.mapAsPage(mapperFacade, orders, pageable, SubjectDto.class);
    }

    @RequestMapping(path = "/{id}",method =  GET)
    public SubjectDto getSubject(@PathVariable(name = "id") Long id) {
        SubjectDto subjectDtoStub = new SubjectDto();
        subjectDtoStub.setId(id);
        subjectDtoStub.setName("test name");
        subjectDtoStub.setDescription("test description");
        subjectDtoStub.setType("PUBLISHING_HOUSE"); //actually do we need change String to SubjectType again?
        return subjectDtoStub;
     }

    //todo add, edit, etc for Subject?

}

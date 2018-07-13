package com.delicacycomics.delicacy.rest;

import com.delicacycomics.delicacy.dto.response.TagDto;
import com.delicacycomics.delicacy.entity.Tag;
import com.delicacycomics.delicacy.util.PageableUtils;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private MapperFacade mapperFacade;

    @RequestMapping(method = GET)
    public Page<TagDto> getTags(@RequestParam(name = "query") String query, Pageable pageable) {
        Page<Tag> tags = new PageImpl<>(Collections.emptyList(), pageable, 0);
        System.out.println("Query = " + query);
        return PageableUtils.mapAsPage(mapperFacade, tags, pageable, TagDto.class);
    }
}

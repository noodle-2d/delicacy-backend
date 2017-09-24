package com.delicacycomics.delicacy.rest;

import com.delicacycomics.delicacy.dto.AddOrderDTO;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/orders")
public class OrderController {

    // @Autowired
    // private OrderService orderService;
    @Autowired
    private MapperFacade mapperFacade;

    @RequestMapping(path = "/add", method = POST)
    public ResponseEntity addOrder(@RequestBody AddOrderDTO addOrderDTO) {
        // todo
//        addOrderDTO.getNote();
//        addOrderDTO.getUserId();
        return new ResponseEntity(HttpStatus.OK);
    }

}

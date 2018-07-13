package com.delicacycomics.delicacy.rest;

import com.delicacycomics.delicacy.component.Secure;
import com.delicacycomics.delicacy.dto.request.OrderAddDto;
import com.delicacycomics.delicacy.dto.request.OrderCheckoutDto;
import com.delicacycomics.delicacy.dto.request.OrderUpdateDto;
import com.delicacycomics.delicacy.dto.response.OrderDto;
import com.delicacycomics.delicacy.dto.response.OrderItemDto;
import com.delicacycomics.delicacy.entity.*;
import com.delicacycomics.delicacy.service.AuthenticationService;
import com.delicacycomics.delicacy.service.OrderService;
import com.delicacycomics.delicacy.util.PageableUtils;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.Collections;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private MapperFacade mapperFacade;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private OrderService orderService;


    @RequestMapping(method = GET)
    @Secure(role = {UserRole.MODERATOR, UserRole.ADMIN})
    public Page<OrderDto> getOrders(Pageable pageable) { // We don't discuss this method, but maybe it will be needed for moderators/admins (list with all orders)?
        Page<Order> orders = orderService.getOrders(pageable); //In that case all data users will be transmit, includes hash, how to improve it/
        System.out.println("Some order lists");
        return PageableUtils.mapAsPage(mapperFacade, orders, pageable, OrderDto.class);
    }

    @RequestMapping(path = "/items", method = GET)
    public Page<OrderItemDto> getBasket(Pageable pageable) {
        Page<OrderItemDto> basket = new PageImpl<>(Collections.emptyList(), pageable, 0);
        System.out.println("Print Basket");
        return PageableUtils.mapAsPage(mapperFacade, basket, pageable, OrderItemDto.class);
    }

    @RequestMapping(path = "/items", method = POST)
    public ResponseEntity addItemToBasket(@RequestBody OrderAddDto orderAddDto) {
        //Do we need order_id in this method? We don't use this in 'specification' :)
        System.out.println(orderAddDto.toString());
        return new ResponseEntity(OK);
    }

    @RequestMapping(path = "/items/{id}", method = DELETE)
    public ResponseEntity deleteItemInBasket(@PathVariable(name = "id") Long id) {
        System.out.println("Deleted ID = " + id);
        return new ResponseEntity(OK);
    }

    @RequestMapping(path = "/items/{id}", method = PUT)
    public ResponseEntity updateAmountOfItemInBasket(@PathVariable(name = "id") Long id, @RequestBody OrderUpdateDto orderUpdateDto) {
        System.out.println("New Amount = " + orderUpdateDto.getAmount());
        return new ResponseEntity(OK);
    }

    @RequestMapping(path = "items/checkout", method = POST)
    public ResponseEntity checkout(@RequestBody OrderCheckoutDto orderCheckoutDto) { //How to make RequestBody is "not required"?
        System.out.println(orderCheckoutDto.toString());
        return new ResponseEntity(OK);
    }

    @RequestMapping(path = "/{identifier}", method = GET)
    public OrderDto getOneOrder(@PathVariable(name = "identifier") String identifier) {
        System.out.println("тестик");
        OrderDto orderDtoStub = new OrderDto();
        orderDtoStub.setId((long) 2131231);
        orderDtoStub.setUser(null);
        orderDtoStub.setNote("testik");
        orderDtoStub.setStatus(UserStatus.ACTIVE); //Fix to OrderStatus?
        orderDtoStub.setIdentifier(identifier);
        System.out.println(orderDtoStub.toString());
        return orderDtoStub;
    }

    //Old From Ilya :)

    @RequestMapping(path = "/create", method = POST)
    public ResponseEntity createOrderFromBusket(HttpServletRequest request) {
        // Getting IP Address of PC from which the request was made
        System.out.println("Remote address: " + request.getRemoteAddr());

        // Getting UserData with the information about current User
        UserData userData = authenticationService.getCurrentUserData();
        System.out.println("User data: " + userData);

        return new ResponseEntity(OK);
    }

}

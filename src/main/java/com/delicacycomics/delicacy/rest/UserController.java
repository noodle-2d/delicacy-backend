package com.delicacycomics.delicacy.rest;

import com.delicacycomics.delicacy.component.Secure;
import com.delicacycomics.delicacy.dto.request.UserEditedDataDto;
import com.delicacycomics.delicacy.dto.response.OrderDto;
import com.delicacycomics.delicacy.dto.response.PersonalPageDto;
import com.delicacycomics.delicacy.dto.response.UserDto;
import com.delicacycomics.delicacy.entity.Order;
import com.delicacycomics.delicacy.entity.UserRole;
import com.delicacycomics.delicacy.entity.UserStatus;
import com.delicacycomics.delicacy.util.PageableUtils;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private MapperFacade mapperFacade;

    @RequestMapping(path = "/{id}", method = GET)
    @Secure(role = {UserRole.MODERATOR, UserRole.ADMIN})
    public UserDto getOneUser(@PathVariable(name = "id") Long id) { // why do we use this method/
        UserDto userDtoStub = new UserDto();
        userDtoStub.setId(id);
        userDtoStub.setLogin("Test Login");
        userDtoStub.setEmail("test@maul.ru");
        userDtoStub.setName("TestName");
        userDtoStub.setPhoneNumber("89083490583");
        userDtoStub.setStatus(UserStatus.ACTIVE);
        userDtoStub.setSurname("TestSurname");
        System.out.println(userDtoStub.toString());
        return userDtoStub;
    }

    @RequestMapping(path = "/me", method = GET)
    @Secure(role = {UserRole.CUSTOMER, UserRole.MODERATOR, UserRole.ADMIN})
    public PersonalPageDto getUserPersonalPage() {
        PersonalPageDto personalPageDtoStub = new PersonalPageDto();
        personalPageDtoStub.setId(Long.valueOf("19"));
        personalPageDtoStub.setLogin("Test Login");
        personalPageDtoStub.setEmail("test@maul.ru");
        personalPageDtoStub.setName("TestName");
        personalPageDtoStub.setPhoneNumber("89083490583");
        personalPageDtoStub.setStatus(UserStatus.ACTIVE);
        personalPageDtoStub.setSurname("TestSurname");
        System.out.println(personalPageDtoStub.toString());
        return personalPageDtoStub;
    }

    @RequestMapping(path = "/me/edit", method = PUT)
    @Secure(role = {UserRole.CUSTOMER, UserRole.MODERATOR, UserRole.ADMIN})
    public ResponseEntity editUserData(@RequestBody UserEditedDataDto userEditedDataDto) {
        return new ResponseEntity(OK);
    }

    @RequestMapping(path = "/me/orders", method = GET)
    @Secure(role = {UserRole.CUSTOMER, UserRole.MODERATOR, UserRole.ADMIN})
    public Page<OrderDto> getUserOrdersOnPersonalPage (Pageable pageable) {
        Page<Order> orders = new PageImpl<>(Collections.emptyList(), pageable, 0);
        System.out.println("Some order lists");
        return PageableUtils.mapAsPage(mapperFacade, orders, pageable, OrderDto.class);
    }

    @RequestMapping(path = "me/orders/{orderIdentificator}", method = GET)
    public OrderDto getDetailsOfUserOrderOnPersonalPage (@PathVariable(name = "orderIdentificator") Long id) {
        OrderDto orderDto = new OrderDto();
        orderDto.setUser(null);
        orderDto.setStatus(UserStatus.ACTIVE);
        orderDto.setNote("КОмменттест");
        orderDto.setIdentifier("TestIdentifier");
        System.out.println(orderDto.toString());
        return orderDto;
    }

    @RequestMapping(path = "{id}/orders", method = GET)
    @Secure(role = {UserRole.MODERATOR, UserRole.ADMIN})
    public Page<OrderDto> getUserOrdersOnAdminArea (@PathVariable(name = "id") Long id, Pageable pageable) {
        Page<Order> orders = new PageImpl<>(Collections.emptyList(), pageable, 0);
        System.out.println("Some order lists");
        System.out.println("Id by user = " + id);
        return PageableUtils.mapAsPage(mapperFacade, orders, pageable, OrderDto.class);
    }

    @RequestMapping(path = "{id}/orders/{orderId}", method = GET)
    @Secure(role = {UserRole.CUSTOMER, UserRole.MODERATOR, UserRole.ADMIN})
    public OrderDto getDetailsOfUserOrderOnAdminArea (@PathVariable(name = "id") Long id, @PathVariable(name = "orderId") Long orderId) {
        OrderDto orderDto = new OrderDto();
        orderDto.setUser(null);
        orderDto.setStatus(UserStatus.ACTIVE);
        orderDto.setNote("КОмменттест");
        orderDto.setIdentifier("TestIdentifier");
        System.out.println(orderDto.toString());
        return orderDto;
    }


}

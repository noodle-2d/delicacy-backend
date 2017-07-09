package com.delicacycomics.delicacy.rest;

import com.delicacycomics.delicacy.component.OrikaMapperFacadeFactory;
import com.delicacycomics.delicacy.dto.ProductDTO;
import com.delicacycomics.delicacy.entity.Product;
import com.delicacycomics.delicacy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class MainPageController {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrikaMapperFacadeFactory orikaMapperFacadeFactory;

    @RequestMapping(method = RequestMethod.GET)
    public List<ProductDTO> findNewestProducts(@RequestParam(name = "quantity", defaultValue = "5") int quantity) {
        List<Product> products = productService.findNewestProducts(quantity);
        return orikaMapperFacadeFactory.getFacade().mapAsList(products, ProductDTO.class);
    }

}
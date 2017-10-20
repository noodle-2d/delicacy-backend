package com.delicacycomics.delicacy.rest;

import com.delicacycomics.delicacy.dto.response.ProductDto;
import com.delicacycomics.delicacy.entity.Product;
import com.delicacycomics.delicacy.service.ProductService;
import com.delicacycomics.delicacy.util.PageableUtils;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private MapperFacade mapperFacade;

    @RequestMapping(method = GET)
    public Page<ProductDto> getProducts(Pageable pageable) {
        Page<Product> products = productService.getProducts(pageable);
        return PageableUtils.mapAsPage(mapperFacade, products, pageable, ProductDto.class);
    }

    @RequestMapping(path = "/{id}", method = GET)
    public ProductDto getProductById(@PathVariable(name = "id") Long id) {
        return null; // todo
    }

}

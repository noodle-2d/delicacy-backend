package com.delicacycomics.delicacy.rest;

import com.delicacycomics.delicacy.dto.request.AddProductDto;
import com.delicacycomics.delicacy.dto.response.ProductDto;
import com.delicacycomics.delicacy.entity.Product;
import com.delicacycomics.delicacy.service.ProductService;
import com.delicacycomics.delicacy.util.PageableUtils;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private MapperFacade mapperFacade;

    @RequestMapping(method = GET)
    public Page<ProductDto> getProducts(@RequestParam(value = "type", required = false) Long type,
                                        @RequestParam(value = "minPrice", required = false) Integer minPrice,
                                        @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
                                        @RequestParam(value = "tag", required = false) String tag,
                                        @RequestParam(value = "query", required = false) String query,
                                        @RequestParam(value = "publisherIds", required = false) Long publisherId,
                                        @RequestParam(value = "publisherLocalIds", required = false) Long publisherLocalId,
                                        @RequestParam(value = "authorIds", required = false) Long authorId,
                                        @RequestParam(value = "artistIds", required = false) Long artistId,
                                        @RequestParam(value = "manufacturerIds", required = false) Long manufacturerIds,
                                        @RequestParam(value = "format", required = false) String format /*FIX THIS TO BOOKFORMAT? OR NOT?*/,
                                        Pageable pageable) {
        Page<Product> products = new PageImpl<>(Collections.emptyList(), pageable, 0);
        return PageableUtils.mapAsPage(mapperFacade, products, pageable, ProductDto.class);
    }

    @RequestMapping(path = "/{id}", method = GET)
    public ProductDto getProductById(@PathVariable(name = "id") Long id) {
        Product product = productService.getProductById(id);
        System.out.println("/id = " + id);
        System.out.println(product.toString());
        return mapperFacade.map(product, ProductDto.class);
    }

    //todo add edit and delete products?

}

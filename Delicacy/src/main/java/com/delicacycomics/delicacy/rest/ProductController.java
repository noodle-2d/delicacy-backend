package com.delicacycomics.delicacy.rest;

import com.delicacycomics.delicacy.dto.ProductDTO;
import com.delicacycomics.delicacy.entity.Product;
import com.delicacycomics.delicacy.service.ProductService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private MapperFacade mapperFacade;

    @RequestMapping(method = GET)
    public List<ProductDTO> findNewestProducts(@RequestParam(name = "quantity", required = false) Integer quantity) {
        List<Product> products = productService.findNewestProducts(quantity);
        return mapperFacade.mapAsList(products, ProductDTO.class);
    }

    @RequestMapping(path = "/page", method = GET)
    public Page<ProductDTO> findNewestProducts(Pageable pageable) {
        return null;
    }

    @RequestMapping(path = "/{id}", method = GET) // /product/5
    public ProductDTO findProductById(@PathVariable(name = "id") Long id) {
        return null; // todo
    }

}

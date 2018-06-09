package com.delicacycomics.delicacy;

import com.delicacycomics.delicacy.entity.Product;
import com.delicacycomics.delicacy.entity.Tag;
import com.delicacycomics.delicacy.repository.ProductRepository;
import com.delicacycomics.delicacy.repository.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
       ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
    }
}

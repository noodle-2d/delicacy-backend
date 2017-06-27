package com.delicacycomics.delicacy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

//    @Bean
//    public CommandLineRunner demo(ProductRepository repository) {
//        return args -> {
//            LOG.info("Products found with findAll():");
//            for (Product product: repository.findAll()) {
//                LOG.info("Product: {}", product);
//            }
//            LOG.info("The end of demo.");
//        };
//    }

}
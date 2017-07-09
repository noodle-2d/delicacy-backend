package com.delicacycomics.delicacy.component;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

@Component
public class OrikaMapperFacadeFactory {

    public MapperFacade getFacade() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        createMappings(mapperFactory);
        return mapperFactory.getMapperFacade();
    }

    private void createMappings(MapperFactory mapperFactory) {
        // todo: add mappings
    }

}
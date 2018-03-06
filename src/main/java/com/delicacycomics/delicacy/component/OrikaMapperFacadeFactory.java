package com.delicacycomics.delicacy.component;

import com.delicacycomics.delicacy.dto.response.AttributeDto;
import com.delicacycomics.delicacy.dto.response.BookDto;
import com.delicacycomics.delicacy.entity.Attribute;
import com.delicacycomics.delicacy.entity.Book;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class OrikaMapperFacadeFactory implements FactoryBean<MapperFacade> {

    @Override
    public MapperFacade getObject() throws Exception {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        createMappings(mapperFactory);
        return mapperFactory.getMapperFacade();
    }

    @Override
    public Class<?> getObjectType() {
        return MapperFacade.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    private void createMappings(MapperFactory mapperFactory) {
        mapperFactory.classMap(Book.class, BookDto.class).byDefault().register();
        mapperFactory.classMap(Attribute.class, AttributeDto.class).byDefault().register();
    }

}

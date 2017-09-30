package com.delicacycomics.delicacy.util;

import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PageableUtils {

    private PageableUtils() { }

    public static <S, D> Page<D> mapAsPage(MapperFacade mapperFacade, Page<S> page, Pageable pageable, Class<D> destinationClass) {
        List<D> destinationTypeList = mapperFacade.mapAsList(page.getContent(), destinationClass);
        return new PageImpl<>(destinationTypeList, pageable, page.getTotalElements());
    }

}

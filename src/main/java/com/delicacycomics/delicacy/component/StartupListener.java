package com.delicacycomics.delicacy.component;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StartupListener {

    @EventListener(value = ContextRefreshedEvent.class)
    @Transactional
    public void handleContextRefresh() {
        System.out.println("Before debug data population");
        //fillEntities();
        System.out.println("After debug data population");
    }

}

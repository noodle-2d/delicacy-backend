package com.delicacycomics.delicacy.component;

import com.delicacycomics.delicacy.Main;
import com.delicacycomics.delicacy.entity.Book;
import com.delicacycomics.delicacy.entity.Tag;
import com.delicacycomics.delicacy.entity.User;
import com.delicacycomics.delicacy.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StartupListener {

   //@Autowired
   //private OrderItemRepository orderItemRepository;
   //@Autowired
   //private OrderRepository orderRepository;
   //@Autowired
   //private PictureRepository pictureRepository;
   //@Autowired
   //private ProductRepository productRepository;
   //@Autowired
   //private SubjectRepository subjectRepository;
   @Autowired
   private TagRepository tagRepository;
   //@Autowired
   //private UserRepository userRepository;



    @EventListener(value = ContextRefreshedEvent.class)
    @Transactional
    public void handleContextRefresh(Object event) { //argument may be deleted, how in that class using context?
        System.out.println("Before debug data population");
       fillEntities(event);
        System.out.println("After debug data population");
    }


    private void fillEntities(Object event) {
        //userRepository.save(new User("jedi", "123", "Alex", "Suverin", "89890807896"));
        //userRepository.save(new User("sith", "1233", "Vanya", "Fydred", "87000212342"));
        //userRepository.save(new User("onemore", "12212", "Bob", "Avokado", "8915261790"));
        //userRepository.save(new User("batmn", "12311", "Bruce", "Wane", "8789089082"));

        tagRepository.save(new Tag("something"));
        tagRepository.save(new Tag("sdf"));
        tagRepository.save(new Tag("third"));
        tagRepository.save(new Tag("fourth"));
    }
}

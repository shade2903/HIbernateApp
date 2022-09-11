package com.haiduk;

import com.haiduk.model.Item;
import com.haiduk.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try{
            session.beginTransaction();

           Person person = session.get(Person.class, 4);
           Item item = session.get(Item.class,1);

           item.setOwner(person);
           person.getItems().add(item);
           session.remove(person);



           session.getTransaction().commit();

        }finally {
            session.close();
        }
    }
}

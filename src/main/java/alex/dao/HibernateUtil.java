package alex.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
public class HibernateUtil {
    private static SessionFactory factory = new Configuration().configure().buildSessionFactory();


    public static Session getSession() {
        try {
            return factory.getCurrentSession();
        } catch (Exception e) {
            return factory.openSession();
        }

    }

}

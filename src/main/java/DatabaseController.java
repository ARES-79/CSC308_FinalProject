import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;
import java.util.List;

public class DatabaseController {
    SessionFactory sessionFactory;
    Session session;

    public void setUp() {
        // configures settings from hibernate.cfg.xml
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            this.sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.session.beginTransaction();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Student getStudentByUsername(String username) {
        System.out.println(username);
        String hql = "FROM Student S WHERE S.username =:username";
        Query query = session.createQuery(hql);
        try {
            query.setParameter("username", username);
            return (Student) query.getSingleResult();
        }
        catch (Exception e){
            return null;
        }
    }
    public Instructor getInstructorByUsername(String username) {
        System.out.println(username);
        String hql = "FROM Instructor I WHERE I.username =:username";
        Query query = session.createQuery(hql);
        try {
            query.setParameter("username", username);
            return (Instructor) query.getSingleResult();
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public void shutDown() {
        this.session.close();
    }
}

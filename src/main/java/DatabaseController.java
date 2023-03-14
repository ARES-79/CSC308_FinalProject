import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.PersistenceException;
import javax.persistence.Query;


@Getter
@Setter
public class DatabaseController {
    private SessionFactory sessionFactory;
    private Session session;

    public void setUp() {
        // configures settings from hibernate.cfg.xml
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            this.sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            this.session = this.sessionFactory.openSession();
            this.session.beginTransaction();
        } catch (Exception e) {
            System.out.println(e);
            throw (e);
        }
    }

    public Student getStudentByUsername(String username) {
        System.out.println(username);
        String hql = "FROM Student S WHERE S.username =:username";
        Query query = session.createQuery(hql);
        try {
            query.setParameter("username", username);
            return (Student) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Instructor getInstructorByUsername(String username) {
        String hql = "FROM Instructor I WHERE I.username =:username";
        Query query = session.createQuery(hql);
        try {
            query.setParameter("username", username);
            return (Instructor) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void saveNewInstructor(Instructor instructor) throws PersistenceException {
        this.session.persist(instructor);
        this.session.getTransaction().commit();

    }
    public void saveNewStudent(Student student) throws PersistenceException {
        this.session.persist(student);
        this.session.getTransaction().commit();

    }

    public void saveCurrentStudent(Student student) throws PersistenceException {
        this.session.saveOrUpdate(student);
        this.session.getTransaction().commit();

    }

    public void shutDown() {
        this.session.close();
    }
}

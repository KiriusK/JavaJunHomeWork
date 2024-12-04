package homework4;

import models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Program {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .buildSessionFactory()) {


            System.out.println("Добавление объектов");
            saveInDb(sessionFactory);

            System.out.println("Чтение объектов");
            readFromDb(sessionFactory);

            System.out.println("Удаление объекта");
            deleteFromDb(sessionFactory,5);

            System.out.println("Снова чтение");
            readFromDb(sessionFactory);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void deleteFromDb(SessionFactory sessionFactory, int id) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.createMutationQuery("DELETE FROM Person WHERE id=" + id).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void readFromDb(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            List<Person> personList = session.createQuery("FROM Person", Person.class).getResultList();
            personList.forEach(p -> p.printInfo());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void saveInDb(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Person pers = new Person("Андрей", 25, "A110");
            session.save(pers);
            pers = new Person("Василий", 26, "A120");
            session.save(pers);
            pers = new Person("Александр", 36, "A130");
            session.save(pers);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

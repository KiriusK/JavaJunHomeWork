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
            System.out.println("Удаление этого объекта");

            deleteFromDb(sessionFactory);
            System.out.println("Снова чтение");

            readFromDb(sessionFactory);
            System.out.println("7 level");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void deleteFromDb(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            List<Person> personList = session.createQuery("FROM Person WHERE id=2", Person.class).getResultList();
            personList.get(0).printInfo();
            session.delete(personList.get(0));
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

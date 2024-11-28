package homework3;

import java.io.*;

public class Program {
    public static void main(String[] args) {
        String fileName = "Objects.bin";
        Person man = new Person("Иванов", 27, "26B");
        man.printInfo();
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(man);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Person manReturned;
        try(ObjectInputStream ois = new ObjectInputStream((new FileInputStream(fileName)))) {
            manReturned = (Person) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        manReturned.printInfo();
    }
}

package models;


import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Person")
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "number")
    private String groupNumber;

    public Person() {
    }

    public Person(String name, int age, String groupNumber) {
        this.name = name;
        this.age = age;
        this.groupNumber = groupNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void printInfo() {
        System.out.printf("Id %d: Студент %s, возраст %d.\nГруппа %s.\n", id, name, age, groupNumber);
    }


}








package homework3;


import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private int age;
    transient private String groupNumber;

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
        System.out.printf("Студент %s, возраст %d.\nГруппа %s.\n", name, age, groupNumber);
    }


}








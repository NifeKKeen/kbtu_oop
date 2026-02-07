package lab1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

class Person {
    enum Gender {MALE, FEMALE}
    private Gender gender;
    private String id, email;

    public String toString() {
        return String.format("Student<id:%s>", id);
    }

    Person(String id, Gender gender, String email) {
        this.id = id;
        this.gender = gender;
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

class DragonLunch {
    Vector<Person> kidnapped = new Vector<>();
    void kidnap(Person p) {
        if (kidnapped.size() > 0 && kidnapped.lastElement().getGender() == Person.Gender.MALE && p.getGender() == Person.Gender.FEMALE) {
            kidnapped.removeLast();
        } else {
            kidnapped.add(p);
        }
    }

    boolean willDragonEatOrNot() {
        return kidnapped.size() > 0;
    }
}

public class Problem5 {
    static void solution() {
        DragonLunch d = new DragonLunch();

        // ArrayList<Person> list = new ArrayList<>(Arrays.asList(
        //         new Person("24B01", Person.Gender.FEMALE, "kanicha@kbtu.kz"),
        //         new Person("24B02", Person.Gender.MALE, "kanich@kbtu.kz"),
        //         new Person("24B03", Person.Gender.FEMALE, "tanicha@kbtu.kz"),
        //         new Person("24B04", Person.Gender.MALE, "tanich@kbtu.kz")
        // ));
        ArrayList<Person> list = new ArrayList<>(Arrays.asList(
                new Person("24B02", Person.Gender.MALE, "kanich@kbtu.kz"),
                new Person("24B04", Person.Gender.MALE, "tanich@kbtu.kz"),
                new Person("24B03", Person.Gender.FEMALE, "tanicha@kbtu.kz"),
                new Person("24B01", Person.Gender.FEMALE, "kanicha@kbtu.kz")
        ));

        System.out.println(d.willDragonEatOrNot());
        for (Person p : list) {
            d.kidnap(p);
            System.out.printf("Person to be kidnapped: %s%n", p);
            System.out.println(d.willDragonEatOrNot());
        }
    }
}

package lab3;

/*
When to use an Interface vs when to use an abstract class. For each “when” provide extended
example(s) (with class/interface codes).
 */
/*
We use abstract classes when classes share a core "is-a" relationship identity and we want
to share fields or common implementation code among closely related classes.
public abstract class Person {
    protected String name;
    protected int age;
}
public class Student extends Person {
    protected int studyYear;
}


We use interfaces to define a contract ("can-do" relationship) or a role that can be
fulfilled by completely unrelated classes. It allows for multiple inheritance of types.

public interface Sellable {
    void sell();
}
public class IPhone implements Sellable {
}
public class Person implements Sellable {
}
 */

public class Problem1 {

}

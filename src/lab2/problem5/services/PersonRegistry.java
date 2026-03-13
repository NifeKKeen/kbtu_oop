package lab2.problem5.services;

import lab2.problem5.models.Animal;
import lab2.problem5.models.Person;

import java.util.Vector;

public class PersonRegistry {
    Vector<Person> people = new Vector<>();
    Vector<Animal> memoizedPets = new Vector<>();
    public void addPerson(Person person) {
        people.add(person);
        memoizedPets.add(person.getPet());
    }
    public void removePerson(Person person) {
        int idx = people.indexOf(person);
        people.remove(idx);
        memoizedPets.remove(idx);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < people.size(); i++) {
            Person person = people.get(i);
            Animal memoizedPet = memoizedPets.get(i);
            if (person.getPet() != memoizedPet) {
                if (memoizedPet == null) {
                    sb.append(String.format("Person %s received pet %s\n", person.getName(), person.getPet().getName()));
                } else {
                    if (person.getPet() == null) {
                        sb.append(String.format("Person %s refused pet %s\n", person.getName(), memoizedPet.getName()));
                    } else {
                        sb.append(String.format("Person %s refused pet %s and got %s instead\n", person.getName(), memoizedPet.getName(), person.getPet().getName()));
                    }
                }
            }
            memoizedPets.set(i, person.getPet());
        }
        return sb.toString();
    }
}

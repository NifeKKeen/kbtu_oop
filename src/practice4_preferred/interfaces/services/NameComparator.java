package practice4_preferred.interfaces.services;

import practice4_preferred.interfaces.model.Student;

import java.util.Comparator;

public class NameComparator implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        return s1.getName().compareTo(s2.getName());
    }
}

package lab2.problem5.models;

public class PhDStudent extends Student {
    protected String researchArea;
    public PhDStudent(String name, int age, int studyYear, String researchArea) {
        super(name, age, studyYear);
        this.researchArea = researchArea;
    }

    @Override
    public String getOccupation() {
        return "PhD_Student";
    }

    @Override
    public String toString() {
        return String.format("PhDStudent<name=%s,age=%d,studyYear=%d,researchArea=%s>", name, age, studyYear, researchArea);
    }

    @Override
    public void assignPet(Animal animal) {
        if (animal instanceof Dog) {
            System.out.println("Dogs are not allowed to be pets of PhD Students");
        } else {
            super.assignPet(animal);
        }
    }
}

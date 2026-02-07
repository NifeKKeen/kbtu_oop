package lab1;

import practice2.CourseStudent;

import java.util.ArrayList;
import java.util.Random;

class Course {
    private String name, description;
    ArrayList<Course> prerequisites = new ArrayList<>();
    int credits;

    Course(String name, String description, ArrayList<Course> prerequisites, int credits) {
        this.name = name;
        this.description = description;
        this.prerequisites = prerequisites;
        this.credits = credits;
    }
}

class GradeBook {
    private String instructorName;
    private Course course;
    private ArrayList<CourseStudent> students = new ArrayList<>();
    static final double MIN_GRADE = 0, MAX_GRADE = 100;

    public GradeBook(String instructorName, Course course) {
        this(instructorName, course, new ArrayList<>());
    }
    public GradeBook(String instructorName, Course course, ArrayList<CourseStudent> students) {
        this.instructorName = instructorName;
        this.course = course;
        this.students = students;
    }

    public void displayMessage() {
        System.out.printf("Welcome, %s! You are reviewing the course %s%n", instructorName, course);
    }

    public void displayGradeReport() {
        outputBarChart(10);
        System.out.println("Class average: " + this.determineClassAverage());

        CourseStudent lowestGradeStudent = this.getLowestGradeStudent();
        System.out.printf("Lowest grade student is %s with grade %.2f%n", lowestGradeStudent.getName(), lowestGradeStudent.getGrade());

        CourseStudent highestGradeStudent = this.getHighestGradeStudent();
        System.out.printf("Highest grade student is %s with grade %.2f%n", highestGradeStudent.getName(), highestGradeStudent.getGrade());
    }

    boolean isOutlier(CourseStudent s) {
        return s.getGrade() < MIN_GRADE || s.getGrade() > MAX_GRADE;
    }

    /**
     * set maxBarsCharWidth to <= 0 if you want to see every bar for every student
     */
    void outputBarChart(int maxBarsCharWidth) {
        System.out.println("Grades distribution:");

        final int bins = 10;
        int[] counters = new int[bins];
        final double valueRange = MAX_GRADE - MIN_GRADE;
        int notCoveredCount = 0;

        for (CourseStudent student : students) {
            if (isOutlier(student)) {
                notCoveredCount++;
                continue;
            }
            double grade = student.getGrade();
            double adjustedGrade = grade - MIN_GRADE;
            int bin = (int) Math.floor(bins * (adjustedGrade / valueRange));
            if (bin >= bins) bin = bins - 1;
            if (bin < 0) bin = 0; // impossible guess?
            counters[bin]++;
        }

        if (notCoveredCount > 0) {
            boolean singleCovered = notCoveredCount == 1;
            System.out.println("NOTE");
            System.out.printf(
                    "%d student%s %s not covered in the range from %.3f to %.3f%n",
                    notCoveredCount,
                    singleCovered ? "" : "s",
                    singleCovered ? "was" : "were",
                    MIN_GRADE, MAX_GRADE
            );
        }
        for (int i = 0; i < bins; i++) {
            double intervalStart = i * valueRange / bins + MIN_GRADE;
            double intervalEnd = (i + 1) * valueRange / bins + MIN_GRADE;
            String bar;
            if (maxBarsCharWidth <= 0) {
                bar = "#".repeat(counters[i]);
            } else {
                bar = "#".repeat((int) Math.ceil(maxBarsCharWidth * counters[i] / MAX_GRADE));
            }
            System.out.printf("%2.0f - %3.0f: %s%n", intervalStart, intervalEnd, bar);
        }
    }

    public void addStudent(CourseStudent student) {
        students.add(student);
    }
    double determineClassAverage() {
        double gradeSum = 0;
        int notCoveredCount = 0;
        for (CourseStudent student : students) {
            if (isOutlier(student)) {
                notCoveredCount++;
                continue;
            }
            gradeSum += student.getGrade();
        }
        if (students.size() - notCoveredCount == 0)
            return 0;
        return gradeSum / (students.size() - notCoveredCount);
    }
    public CourseStudent getLowestGradeStudent() {
        double minGrade = 100;
        CourseStudent lowestGradeStudent = null;
        for (CourseStudent student : students) {
            if (isOutlier(student)) continue;
            if (student.getGrade() < minGrade) {
                minGrade = student.getGrade();
                lowestGradeStudent = student;
            }
        }
        return lowestGradeStudent;
    }
    public CourseStudent getHighestGradeStudent() {
        double maxGrade = 0;
        CourseStudent highestGradeStudent = null;
        for (CourseStudent student : students) {
            if (isOutlier(student)) continue;
            if (student.getGrade() > maxGrade) {
                maxGrade = student.getGrade();
                highestGradeStudent = student;
            }
        }
        return highestGradeStudent;
    }
}

public class Problem4 {
    public static void solution() {
        Course course = new Course( "CS101", "Object-Oriented Programming and Design", new ArrayList<>(), 3);
        GradeBook gradeBook = new GradeBook("Izbassar Asylzhan", course);
        gradeBook.displayMessage();

        // gradeBook.addStudent(new CourseStudent("Kanich", "123456789", 100));
        // gradeBook.addStudent(new CourseStudent("Adiletik", "987654321", 95));
        // gradeBook.addStudent(new CourseStudent("Nurirus", "098765432", -100));
        // gradeBook.addStudent(new CourseStudent("timu4in", "69696969", 1001));

        // Random rnd = new Random();
        // for (int i = 0; i < 1000; i++) {
        //     double grade;
        //     if (Math.random() < 1. / 50) {
        //         grade = Math.random() * 20;
        //     } else if (Math.random() < 1. / 100) {
        //         grade = 30 + Math.random() * 20 - 10;
        //     } else {
        //         grade = (rnd.nextGaussian() * 10) + 78;
        //     }
        //     gradeBook.addStudent(new CourseStudent("Student" + i, Integer.toString(i), grade));
        // }

        // System.out.println("Please, input students' grades:");
        // ArrayList<CourseStudent> students = CourseStudent.inputStudents(10);
        //
        // for (CourseStudent student : students) {
        //     gradeBook.addStudent(student);
        // }

        gradeBook.displayGradeReport();
    }
}

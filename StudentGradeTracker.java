
import java.util.*;

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of students: ");
        int numStudents = sc.nextInt();

        if(numStudents <= 0) {
            System.out.println("Please enter a valid number of students");
        }

        int[] grades = new int[numStudents];

        for(int i=0; i<numStudents; i++) {
            System.out.print("Enter the grade for student " + (i + 1) + ": ");
            grades[i] = sc.nextInt();
        }

        if(grades.length == 0) {
            System.out.println("No grades entered.");
            return;
        }

        int total = 0;
        int highest = grades[0];
        int lowest = grades[0];

        for(int grade : grades) {
            total += grade;
            if(grade > highest) {
                highest = grade;
            }
            if(grade < lowest) {
                lowest = grade;
            }
        }
        double average = (double) total/grades.length;

        System.out.println("Grades entered: " + Arrays.toString(grades));
        System.out.println("Average grade: " + average);
        System.out.println("Highest grade: " + highest);
        System.out.println("Lowest grade: " + lowest);
    }
}
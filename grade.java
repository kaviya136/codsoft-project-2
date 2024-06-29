import java.util.Scanner;

public class StudentGradeCalculator {

    static class Student {
        String name;
        int[] marks;
        int totalMarks;
        double averagePercentage;
        char grade;

        Student(String name, int numberOfSubjects) {
            this.name = name;
            this.marks = new int[numberOfSubjects];
            this.totalMarks = 0;
            this.averagePercentage = 0.0;
            this.grade = 'F';
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get number of students
        int numberOfStudents = getPositiveIntInput(scanner, "Enter number of students: ");

        // Get number of subjects
        int numberOfSubjects = getPositiveIntInput(scanner, "Enter number of subjects: ");

        // Create an array to store student information
        Student[] students = new Student[numberOfStudents];

        // Input student data
        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("Enter details for student " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            students[i] = new Student(name, numberOfSubjects);

            // Input marks for each subject
            for (int j = 0; j < numberOfSubjects; j++) {
                students[i].marks[j] = getPositiveIntInput(scanner, "Marks for subject " + (j + 1) + ": ");
                students[i].totalMarks += students[i].marks[j];
            }

            // Calculate average percentage and grade
            students[i].averagePercentage = (double) students[i].totalMarks / numberOfSubjects;
            students[i].grade = calculateGrade(students[i].averagePercentage);
        }

        // Display student data
        displayStudentGrades(students);

        scanner.close();
    }

    // Method to get a positive integer input from the user
    private static int getPositiveIntInput(Scanner scanner, String prompt) {
        int input = -1;
        while (input < 0) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input < 0) {
                    System.out.println("Please enter a positive integer.");
                }
            } else {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next();  // Clear the invalid input
            }
        }
        scanner.nextLine();  // Consume the newline
        return input;
    }

    // Method to calculate grade based on average percentage
    public static char calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return 'A';
        } else if (averagePercentage >= 80) {
            return 'B';
        } else if (averagePercentage >= 70) {
            return 'C';
        } else if (averagePercentage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    // Method to display student grades
    public static void displayStudentGrades(Student[] students) {
        System.out.println("\nStudent Grades:");
        for (Student student : students) {
            System.out.printf("Name: %s, Total Marks: %d, Average Percentage: %.2f, Grade: %c%n",
                    student.name, student.totalMarks, student.averagePercentage, student.grade);
        }
    }
}

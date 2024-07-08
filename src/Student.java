import java.util.InputMismatchException;
import java.util.Scanner;

public class Student extends Module {

    private String stID;
    private String stName;

    public Student(String stName, String stID) {
        this.stID = stID;
        this.stName = stName;
    }

    public void EnterStudentMarks() {

        float marks1 = returnValidMarks("Enter Marks of Module 1: ");
        float marks2 = returnValidMarks("Enter Marks of Module 2: ");
        float marks3 = returnValidMarks("Enter Marks of Module 3: ");

        setMarks1(marks1);
        setMarks2(marks2);
        setMarks3(marks3);


        System.out.println("\nMarks have been added successfully !\n");
    }

    public boolean checkEmptyMarks() {
        return getMarks1() == -1 && getMarks2() == -1 && getMarks3() == -1;
    }


    private float returnValidMarks(String prompt) {

        Scanner input = new Scanner(System.in);
        float value;

        while (true) {
            try {
                while (true) {
                    System.out.print(prompt);
                    value = input.nextFloat();
                    if (value >= 0 && value <= 100) {
                        return value;
                    } else {
                        System.out.println("Invalid Marks !. Marks should be in range 0 - 100\n ");
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter a number !");
                input.next();

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                input.next();
            }
        }
    }

//------------- Getters and Setters ------------------
    public String getStID() {
        return stID;
    }

    public void setStID(String stID) {
        this.stID = stID;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

}


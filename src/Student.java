import java.util.InputMismatchException;
import java.util.Scanner;

public class Student {

    private String stID;
    private String stName;
    private Module moduleMarks;

    public Student (String stName, String stID){
        this.stID = stID;
        this.stName = stName;
    }

    public void EnterStudentMarks(){

        float marks1 = returnValidMarks("Enter Marks of Module 1: ");
        float marks2 = returnValidMarks("Enter Marks of Module 2: ");
        float marks3 = returnValidMarks("Enter Marks of Module 3: ");

        this.moduleMarks = new Module(marks1, marks2, marks3);

        System.out.println("\nMarks have been added successfully !\n");
    }

    public void displayStudentDetails(){
        System.out.println("\n---------Student Details---------\n");
        System.out.println("Student ID     : "+this.stID);
        System.out.println("Student Name   : "+this.stName);
        System.out.println("Module 1 marks : "+this.moduleMarks.getMarks1());
        System.out.println("Module 2 marks : "+this.moduleMarks.getMarks2());
        System.out.println("Module 3 marks : "+this.moduleMarks.getMarks3());
        System.out.println("Total marks    : "+(this.moduleMarks.getMarks1()+this.moduleMarks.getMarks2()+this.moduleMarks.getMarks3()));
        System.out.println("Average marks  : "+this.moduleMarks.getAverage());
        System.out.println("Grade          : "+this.moduleMarks.getGrade());
    }


    private float returnValidMarks(String prompt){

        Scanner input = new Scanner(System.in);
        float value;

        while (true) {
            try {
                System.out.print(prompt);
                value =  input.nextFloat();
                if(value < 0 || value > 100){
                    return value;
                } else {
                    System.out.println("Invalid Marks !. Marks should be in range 0-100\n ");
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter a number !");

            } catch (Exception e) {
                System.out.println("Error: "+e.getMessage());
            }
        }
    }

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


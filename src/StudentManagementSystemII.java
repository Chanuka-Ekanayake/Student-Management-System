//------------------------------------------------- Personal info ----------------------------------------------
//Author: Chanuka Ekanayake
//UoW ID: w2083948
//IIT ID: 20230100
//Last update: 7-July-2024


//----------------------------------------------- Coursework Part II ----------------------------------------------

//Importing Packages
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class StudentManagementSystemII {

    private static  String[][] student_details = new String[100][3];
    private static final Student [] stModule_evaluator = new Student[100];


    public static void main(String[] args) {

        //String [][] student_details = new String [100][4];
        String file_name = "Student data.txt";


        //Initialize the array
        initialize(student_details);
        initializeModuleEvaluator(stModule_evaluator);

        String choice = "";

        while(!choice.equals("0")) {

            //MainMenu
            System.out.println("\n\t\t\t-------- Student Management System --------\n");
            System.out.println("1. Check available seats");
            System.out.println("2. Register Student (with ID)");
            System.out.println("3. Delete Student");
            System.out.println("4. Find Student (with Student ID)");
            System.out.println("5. Store Student details into a file");
            System.out.println("6. Load details from the file to the system");
            System.out.println("7. View the list of students based on their names");
            System.out.println("8. Enter Student Module Evaluator");
            System.out.println("0. Exit program\n");

            choice = returnInput("Enter choice: ");

            switch (choice) {
                case "1":
                    availableSeats();
                    break;
                case "2":
                    Register();
                    break;
                case "3":
                    deleteStudent();
                    break;
                case "4":
                    findStudent();
                    break;
                case "5":
                    saveDetails(file_name);
                    break;
                case "6":
                    loadDetails(file_name);
                    break;
                case "7":
                    viewByNames();
                    break;
                case "8":
                    studentModuleEvaluator();
                    break;
                case "0":
                    if(returnYorN("Do you want to save data before exiting? (Y/N): ")){
                        saveDetails(file_name);
                    }
                    System.out.println("\nProgram terminated.\nHave a nice day!");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter choice 1 - 8 or \"0\" to exit\n");
            }
        }

    }


//--------------------------------------- Main menu Methods -------------------------------------

    // ----------------> CHOICE 1
    public static void availableSeats(){
        /*
        Check the available seats by iterating through the main array and checking
        whether the StudentID is empty or not
         */

        System.out.println("\n--- Available Seats ---\n");


        int availability = 0;

        for(String[] l: student_details){
            for(String element: l){
                if(element.equals("-")){
                    availability++;
                    break;
                }
            }
        }
        System.out.println("Number of available seats: "+availability+" out of "+student_details.length+"\n");


        while(true){
            String mainMenu = returnInput("Enter \"0\" to enter Main menu: ");

            if(mainMenu.equals("0")) {
                return;
            }
        }
    }


    // ----------------> CHOICE 2
    public static void Register (){
        /*
        Gets user inputs to register a student and store the details on the main array
        Gets user inputs on Student ID, Student First name, Student last name and store
         */

        System.out.println("\n--- Register Student ---\n");

        boolean Registered = false;
        String confirmation = "";

        for (int i = 0; i < student_details.length; i++) {
            if (student_details[i][0].equals("-")) {

                String stID;
                //Validating Student ID
                while (true) {
                    stID = studentID();

                    if (stID.equals("-")) {
                        System.out.println("Sorry ! Only student with IDs can be registered.\n");
                        return;

                    } else if (studentIndex(stID, student_details) != -1) {
                        System.out.println("Sorry ! " + stID + " is already existing. Please enter a different ID.\n");
                    }
                    else {
                        break;
                    }
                }

                String studentName = returnInput("Enter Student name: ");

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date = simpleDateFormat.format(new Date());

                System.out.println("Student name    : " + studentName);
                System.out.println("Student ID      : " + stID);
                System.out.println("Registered date : " + date);


                //Saving student details
                if (returnYorN("\nConfirm registration (Y/N): ")) {
                    student_details[i][0] = stID;
                    student_details[i][1] = studentName;
                    student_details[i][2] = date;

                    System.out.println("Registration Completed\n");
                    Registered = true;
                    break;
                }
            }
        }

        if(Registered){

            if (returnYorN("Do you want to register another student (Y/N): ")) {
                Register();
            }
            else {
                System.out.println("Returning to main menu...\n");
            }
        }
        else{
            if(confirmation.equals("N")){
                System.out.println("Registration Declined !");
            }
            else {
                System.out.println("Sorry no space available to register the student !");
            }
            System.out.println("Returning to main menu...\n");
        }
    }

    // ----------------> CHOICE 3
    public static void deleteStudent(){
        /*
            Delete details of a student by taking the student ID as the input.
            Method replace the element containing student details with "-" to
            indicate the slot is available for future registrations
         */

        System.out.println("\n--- Delete Student ---\n");

        //Returning to Main menu if the initial array is empty
        if(emptyDataBase(student_details)){
            System.out.println("Sorry ! No student details saved to delete.");

            String choice = "";
            while (!choice.equals("0")){
                choice= returnInput("Enter \"0\" to return to Main menu: ");
            }
            System.out.println("Returning to main menu...\n");
            return;
        }

        String stID;
        int stIndex;

        //Validating Student ID
        while(true) {
            stID = studentID();
            stIndex = studentIndex(stID, student_details);

            if (stIndex == -1) {
                System.out.println("The ID you entered was not found!. Please check the ID and enter again");
                System.out.println("Enter \"0\" for ID to return main menu\n");

            } else if (stID.equals("-")) {
                System.out.println("Returning to main menu...\n");
                return;
            } else {
                break;
            }
        }

        displayDetails(stIndex, student_details); //Display details of the student before delete


        if (returnYorN("Confirm deleting the student with above details (Y/N): ")) {
            student_details[stIndex][0] = "-";
            student_details[stIndex][1] = "-";
            student_details[stIndex][2] = "-";

            System.out.println("Completed deleting student with ID " + stID+"\n");

        } else{
            System.out.println("Decline deleting student " + stID + "\n");
        }


        if (returnYorN("Do you want to delete another Student? (Y/N): ")) {
            deleteStudent();
        } else{
            System.out.println("Returning to main menu...\n");
        }
    }

    // ----------------> CHOICE 4
    public static void findStudent(){
        /*
            Finding student by getting the studentID from the user.
            If the student ID is not available allows user to enter again until user gets
            an available studentID or enter 0
         */

        System.out.println("\n--- Find Student ---\n");

        //Returning to Main menu if the initial array is empty
        if(emptyDataBase(student_details)){
            System.out.println("Sorry ! No student details saved to Find Students.");

            String choice = "";
            while (!choice.equals("0")){
                choice= returnInput("Enter \"0\" to return to Main menu: ");
            }
            System.out.println("Returning to main menu...\n");
            return;
        }

        String stID;
        int stIndex;

        //Validating Student ID
        while (true){
            stID = studentID();
            stIndex = studentIndex(stID,student_details);

            if(stID.equals("-")){
                System.out.println("Returning to main menu...\n");
                return;
            }
            else if(stIndex == -1){
                System.out.println("The ID you entered was not found !. Please check the ID and enter again");
                System.out.println("Enter \"0\" for ID to return main menu\n");
            }
            else {
                displayDetails(stIndex,student_details);
                break;
            }
        }

        if (returnYorN("Do you want to Find another Student? (Y/N): ")){
            findStudent();
        } else {
            System.out.println("Returning to main menu...\n");
        }

    }

    // ----------------> CHOICE 5
    public static void saveDetails(String fileName){
        /*
            The method first get the user input for the file location if user wish to
            save somewhere except the default file. If the file does not exist; it will create a file automatically.
            By iterating through element by element in the main data list method save each element on each line
         */

        System.out.println("\n--- Save Student Data ---\n");

        //Returning to Main menu if the initial array is empty
        if(emptyDataBase(student_details)){
            System.out.println("Sorry ! No student details in the system to save.");

            String choice = "";
            while (!choice.equals("0")){
                choice= returnInput("Enter \"0\" to return to Main menu: ");
            }
            System.out.println("Returning to main menu...\n");
            return;
        }

        //Validating source file
        String saveFile;
        System.out.println("The default saving file is : "+fileName);


        if(!returnYorN("Do you want to change the default file? (Y/N): ")){
            saveFile = fileName;
        }else {
            saveFile = returnInput("Enter/Create file name with file type (Ex: New File.txt): ");
        }

        File file = new File(saveFile);

        if(!file.exists()){
            System.out.println("The File: "+file.getName()+"does not exist.");

            if(returnYorN("Do you want to create a new file names "+file.getName()+"? (Y/N): ")){
                try {
                    boolean newFile = file.createNewFile();
                    if (newFile) {
                        System.out.println(saveFile + " created successfully !");

                        //Checking if the user has entered a .txt file or other file type can not be written
                        if(!file.canWrite()){
                            System.out.println(saveFile+" can not be written ! Please enter a .txt file");
                            saveDetails(fileName);
                        }

                    } else {
                        System.out.println("Error! "+saveFile+" did not created !");
                        System.out.println("Returning to Main menu...\n");
                        return;
                    }
                }catch (IOException e){
                    System.out.println("Sorry! Request can not be completed due to IO error\nError: "+e.getMessage());
                    System.out.println("Returning to Main menu...\n");
                    return;

                }catch (Exception e){
                    System.out.println("Sorry! Request can not be completed\nError: "+e.getMessage());
                    System.out.println("Returning to Main menu...\n");
                    return;
                }
            } else {
                System.out.println("No source file to store data !");
                System.out.println("Returning to Main menu...\n");
                return;
            }
        }


        //User confirmation on saving data
        System.out.println("\nWarning: Saving System data will delete the current data in the file and overwrite System data");
        System.out.println("If the file contains unloaded system data please import file data first before saving !\n");


        if(!returnYorN("Confirm saving system data to the file (Y/N): ")){
            System.out.println("\nReturning to Main menu...\n");
            return;
        }

        try{
            //Save data to the file
            FileWriter fileWriter = new FileWriter(file);
            for(String[] l:student_details){
                for(String element:l){
                    if(!element.equals("-")){
                        fileWriter.write(element+"\n");
                    }
                }
            }
            System.out.println("Student data has been saved successfully !\n");
            fileWriter.close();

        }catch (IOException e){
            System.out.println("Sorry request can not be completed due to an IO error\n Error: "+e.getMessage()+"\n");
        }catch (Exception e){
            System.out.println("Sorry request can not be completed due to an error\nError: "+e.getMessage()+"\n");
        }

        while (true){
            String mainMenu = returnInput("Enter \"0\" to return to Main menu: ");

            if(mainMenu.equals("0")){
                System.out.println("\nRetuning to Main menu...\n");
                return;
            }
        }
    }


    // ----------------> CHOICE 6
    public static void loadDetails (String fileName){
        /*
            Method to save the data in the system to a file. Process will be failed if the file contains data more
            than 100 student, corrupted data  or with any other file handling errors
         */
        System.out.println("\n--- Load Student Data ---\n");

        //Initialize the source File
        String saveFile;
        System.out.println("The default file to load data is: "+fileName);

        if(returnYorN("Do you want to change the loading file? (Y/N): ")){
            saveFile = returnInput("Enter File name with file type (Ex: New File.txt): ");
        }else {
            saveFile = fileName;
        }

        File file = new File(saveFile);

        //Validating the File
        if(!file.exists()) {
            System.out.println("The file " + saveFile + " does not exists. Please check the filename and try again !\n");
            System.out.println("Returning to Main menu...");
            return;
        }else if(!file.canRead()){
            System.out.println("The file you entered was unable to read. Please enter a .txt file\n");
            loadDetails(fileName);
        } else{
            if(!returnYorN("Please confirm uploading data in file \""+file.getName()+"\" (Y/N): ")){
                System.out.println("Returning to Main menu...\n");
                return;
            } else {
                System.out.println("\nUploading file data...\n");
            }
        }

        String[][] temp = student_details.clone(); //Cloning the array if in case an error occur while uploading data

        try{
            Scanner fileReader = new Scanner(file);

            //Checking the file contain data or empty
            if(!fileReader.hasNextLine()){
                System.out.println("File entered does not contain any data! Please check the file again.\n");
                System.out.println("Returning to Main menu...\n");
                return;
            }

            //Uploading file data to the array
            String text;
            for(int l =0; l<student_details.length; l++) {
                if(student_details[l][0].equals("-")) {
                    for (int i = 0; i < student_details[l].length; i++) {
                        if(fileReader.hasNextLine()) { // Handles getting IO exception when lines are finished
                            text = fileReader.nextLine();

                            if (text.startsWith("w") && (text.length() == 8)) {
                                if (i == 0) {
                                    student_details[l][i] = text;
                                } else {
                                    student_details[l + 1][0] = text; //Solve the issue if an ID is found and relevant array slot is changed
                                    break;
                                }

                            } else if (Character.isAlphabetic(text.charAt(0))) {
                                if (i == 1 ) {
                                    student_details[l][i] = text;
                                } else {
                                    student_details[l][i] = "-";
                                }

                            } else if (Character.isDigit(text.charAt(0))) {
                                if (i == 2) {
                                    student_details[l][i] = text;
                                } else {
                                    student_details[l][i] = "-";
                                }

                            } else {
                                student_details[l][i] = "-";
                            }
                        }
                    }
                }
            }


            System.out.println("Data has been uploaded to the system successfully !");
            System.out.println("INFO: Please note that corrupted data in the file has been recorded as \"-\"\n");
            fileReader.close();

            String mainMenu = "";

            while (!mainMenu.equals("0")){
                mainMenu = returnInput("Enter \"0\" to enter Main menu: ");
            }
            System.out.println("Returning to Main menu...\n");


        }catch (IOException e){
            System.out.println("Sorry! Request can not be completed due to IO error\nError: "+e.getMessage());
            System.out.println("Uploading data declined !\n");
            student_details = temp; //Updating the array by deleting the data added by the file and restoring previous data
            System.out.println("Returning to Main menu...\n");

        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Sorry! File contained data more than free system storage");
            System.out.println("Uploading data declined !\n");
            student_details = temp; //Updating the array by deleting the data added by the file and restoring previous data

        }catch (Exception e){
            System.out.println("Sorry! Request can not be completed due to IO error\nError: "+e.getMessage());
            System.out.println("Uploading data declined !\n");
            student_details = temp; //Updating the array by deleting the data added by the file and restoring previous data

        }
    }


    // ----------------> CHOICE 7
    public static void viewByNames(){
        /*
            A list of students names have been created by referencing the main array containing student details.
            Method sorts the array according to UNICODE characters (Alphabetically)
         */

        System.out.println("\n--- View list of Student ---\n");

        if(emptyDataBase(student_details)){
            System.out.println("Sorry ! No student details saved to view Students by Name.");

            String choice = "";
            while (!choice.equals("0")){
                choice= returnInput("Enter \"0\" to return to Main menu: ");
            }
            System.out.println("Returning to main menu...\n");
            return;
        }

        //Check the loop to initialize the names array size
        int arrayLength = 0;
        for(String[] l: student_details){
            if(!l[0].equals("-")){
                arrayLength++;
            }
        }
        String[] studentNamesArray = new String[arrayLength];

        //Saving student names into a new array in order for sort and display
        for (int i = 0; i < student_details.length; i++) {
            if(!student_details[i][1].equals("-")) {
                studentNamesArray[i] = student_details[i][1] + " " +" - "+student_details[i][0];
            }
        }

        //Sorting the array (Selection sort)
        String temp;
        for (int i = 0; i < studentNamesArray.length; i++) {
            for (int j = i+1; j < studentNamesArray.length; j++) {
                if(studentNamesArray[i].compareToIgnoreCase(studentNamesArray[j]) > 0){ //Compare with UNICODE characters

                    temp = studentNamesArray[i];
                    studentNamesArray[i] = studentNamesArray[j];
                    studentNamesArray[j] = temp;
                }
            }
        }

        //Display the sorted list
        for (int i = 0; i < studentNamesArray.length; i++) {
            System.out.println((i+1)+". "+studentNamesArray[i]);
        }
        System.out.println(); //To make a gap between Student name list


        String choice = "";
        while (!choice.equals("0")) {
            choice = returnInput("Enter \"0\" to return: ");
        }
    }

    // ----------------> CHOICE 8
    public static void studentModuleEvaluator(){

        System.out.println("\n--- Student Module Evaluator ---\n");


        String choice = "";
        while (!choice.equals("0")){

            //Submenu
            System.out.println("1. Add a student");
            System.out.println("2. Add Module marks of a student");
            System.out.println("3. Check details of a student");
            System.out.println("0. Exit to Main menu\n");

            choice = returnInput("Enter choice: ");

            switch (choice){
                case "1":
                    addStudent_moduleEvaluator();
                    break;
                case "2":
                    addMarks_moduleEvaluator();
                    break;
                case"3":
                    checkDetails();
                    break;
                case "0":
                    System.out.println("Returning to Main menu...\n");
                    break;
                default:
                    System.out.println("Invalid choice ! Please select a choice 1, 2 or \"0\" to exit\n");
            }
        }

    }


//------------------------------ Sub Menu (Module Evaluator) Methods -------------------------------

    // ----------------> CHOICE 1
    public static void addStudent_moduleEvaluator(){
        /*
            Add a student to the module evaluator. Only the students who have been registered initially can be added
            to the Student module evaluator.
         */

        System.out.println("\n--- Add Student for the  Module Evaluator ---\n");

        for (int i = 0; i < stModule_evaluator.length ; i++) {
            if (stModule_evaluator[i].getStName().equals("-") && stModule_evaluator[i].getStID().equals("-")) {

                String stID = studentID();

                int stIndex = studentIndex(stID, student_details);

                //Validating the Student
                if (stIndex == -1) {
                    System.out.println("\nSorry! There is no student registered on ID " + stID);
                    System.out.println("Only registered students can be added to the Module evaluator\n");

                    if (returnYorN("Do you want to check registered Student details? (Y/N): ")) {
                        viewByNames();
                    } else {
                        System.out.println("Returning to Module Evaluator menu...\n");
                        return;
                    }


                } else {
                    for(Student student: stModule_evaluator){
                        if(student.getStID().equals(stID)){
                            System.out.println("The student ID you entered was already registered in Module Evaluator ! \n");

                            String returnBack = "";
                            while (!returnBack.equals("0")) {
                                returnBack = returnInput("Enter \"0\" to return to Module Evaluator menu: ");
                            }
                            System.out.println("Returning to Module Evaluator menu...\n");
                            return;
                        }
                    }


                    displayDetails(stIndex, student_details);
                }


                if(returnYorN("Confirm adding student with above details to the evaluator (Y/N): ")){
                    stModule_evaluator[i].setStID(stID);
                    stModule_evaluator[i].setStName(student_details[stIndex][1]+" "+student_details[stIndex][2]);

                    System.out.println("\nStudent details added successfully !\n");
                } else {
                    System.out.println("Returning to Module Evaluator menu...\n");
                    return;
                }

                if(returnYorN("Do you want to add another student? (Y/N): ")){
                    addStudent_moduleEvaluator();
                } else {
                    System.out.println("Returning to Module Evaluator menu...\n");
                    return;
                }
            }
        }
    }


    // ----------------> CHOICE 2
    public static void addMarks_moduleEvaluator(){
        /*
            Add marks to the student with relevant ID which already has been registered on the
            Module Evaluator
         */
        System.out.println("\n--- Add Student Marks for the  Module Evaluator ---\n");

        String stID = studentID();

        boolean marksAdded = false;

        for (int i = 0; i < stModule_evaluator.length; i++) {
            if(stModule_evaluator[i].getStID().equals(stID)){
                if(!stModule_evaluator[i].checkEmptyMarks()){
                    System.out.println(stModule_evaluator[i].getStName()+"'s Modules marks have been already added.");
                    String changeMarks = returnInput("Do you want to change the marks? (Y/N): ");
                    if(changeMarks.equals("Y")){
                        stModule_evaluator[i].EnterStudentMarks();
                        marksAdded = true;
                    } else{
                        System.out.println("Returning to Module Evaluator menu...\n");
                        return;
                    }
                }else {
                    stModule_evaluator[i].EnterStudentMarks();
                    marksAdded = true;
                }
            }
        }

        if(!marksAdded){
            System.out.println("The Student ID you entered was not added to the Module Evaluator !");
            System.out.println("Please check again !\n");
        }

        if(returnYorN("Do you want to add marks for another student? (Y/N): ")){
            addMarks_moduleEvaluator();
        }else {
            System.out.println("Returning to Module Evaluator menu...\n");
        }
    }

    // ----------------> CHOICE 3
    public static void checkDetails(){
        String stID = studentID();

        boolean marksAdded = false;

        for (int i = 0; i < stModule_evaluator.length; i++) {
            if(stModule_evaluator[i].getStID().equals(stID)){

                stModule_evaluator[i].displayStudentDetails();
                marksAdded = true;
            }
        }

        if(!marksAdded){
            System.out.println("The Student ID you entered was not added to the Module Evaluator !");
            System.out.println("Please check again !\n");
        }

        if(returnYorN("Do you want to check details of another student? (Y/N): ")){
            checkDetails();
        }else {
            System.out.println("Returning to Module Evaluator menu...\n");
        }

    }



//--------------------------------------- Additional Methods --------------------------------------

    public static void initialize(String[][] array){
        /*
        Use to initialize the main array containing student details; each and every element to "-"
        for better identification as empty data

        Arguments:
            array: 2D String array where student details are stored
         */

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) array[i][j] = "-";
        }
    }

    public static String returnInput(String prompt) {
        /*
        Method use to trim the user input to avoid blank line inputs

        Arguments:
            prompt (String) - The prompt to get the input from the user
        Returns:
            String: trim the user input
         */

        Scanner input = new Scanner(System.in);

        while (true) {

            System.out.print(prompt);
            String value = input.nextLine().trim();
            System.out.println();

            if(value.isEmpty()){
                System.out.println("Sorry ! Input can not be empty\n");
            }
            else {
                return value;
            }


        }
    }

    public static String studentID (){

        /*
        Method to return Student ID by checking the limitations

        Arguments:
            2D String Array: Get the array as an argument since the viewByNames function is called inside
                             in order if the user forgot relevant Student ID which is already stored

        Returns:
            String: Student ID if conditions met or "-" if user enter 0
         */

        Scanner input = new Scanner(System.in);
        while (true){

            System.out.print("Enter Student ID: ");
            String stID = input.nextLine().trim();
            System.out.println();

            if(stID.length() == 8 && stID.startsWith("w")){
                return stID;
            }
            else if (stID.equals("0")){
                return "-";
            }
            else{
                System.out.println("Invalid ID !. Please enter the 8 digit student ID  starts with \"w\" or \"0\" to exit\n");


                if(returnYorN("Do you want to check the student details (Y/N): ")){
                    viewByNames();
                }
            }
        }
    }




    public static int studentIndex(String stID, String[][] array){
        /*
        Method to find the uniqueness of the Student ID

        Arguments:
            stID (String)          : Student ID of the student
            array (2D String Array): Array containing student details


        Returns:
            idIndex(int)    : Index of the array containing details of the student under the stID
                              -1 if the stID does not found in the array
         */

        int idIndex = -1;

        for (int i = 0; i < array.length; i++) {
            if(stID.equals(array[i][0])){
                idIndex = i;
            }
        }
        return idIndex;
    }


    public static void displayDetails(int index, String[][] array){
        /*
        Display the details of a specific student under the specific index of the array
        containing the student details.

        Arguments:
            index (Integer)        : Index of the array where the StudentID is located. Can be obtained by calling
                                     studentIndex method

            array (2D String Array): Array containing student details
         */

        System.out.println("\nStudent Name    : "+array[index][1]);
        System.out.println("Student ID      : "+array[index][0]);
        System.out.println("Registered Date : "+array[index][2]+"\n");

    }

    public static boolean returnYorN(String prompt){
        /*
        Method to minimize the lines of code on questions which expecting Y or N as the
        input from the user

        Arguments:
            prompt (String): Prompt / Question to ask from the user to enter Y or N

        Returns:
            Returns Y or N
         */

        Scanner input = new Scanner(System.in);
        String choice;

        while (true) {
            System.out.print(prompt);
            choice = input.nextLine().trim();
            if(choice.equalsIgnoreCase("Y")){
                return true;
            } else if (choice.equalsIgnoreCase("N")) {
                return false;
            }else {
                System.out.println("Please enter \"Y\" or \"N\"");
            }
        }

    }

    public static boolean emptyDataBase(String[][] array){
        /*
            Method to check the database is empty or not

            Arguments:
                array (2D String Array): Array containing student details

            Returns:
                emptyBase (boolean)    : Returns true if main list is empty
         */

        boolean emptyBase = true;

        for(String[]l:array){
            for(String element:l){
                if(!element.equals("-")){
                    emptyBase = false;
                    break;
                }
            }
        }
        return emptyBase;
    }


    public static void initializeModuleEvaluator(Student[] array){
        /*
            Initializing the student name and ID of the array consisting Student for calculations

            Arguments:
                array (Student): Array containing student objects
         */

        for (int i = 0; i < array.length; i++) {
            array[i] = new Student("-","-");
        }

    }

}
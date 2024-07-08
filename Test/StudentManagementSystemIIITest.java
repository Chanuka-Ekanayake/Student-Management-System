import org.junit.Test;

import static org.junit.Assert.*;

public class StudentManagementSystemIIITest {

    @Test
    public void initialize() {
        String[][] array = new String[3][3];
        StudentManagementSystemIII.initialize(array);

        for (String[] row : array) {
            for (String element : row) {
                assertEquals("-", element);
            }
        }
    }

    @Test
    public void returnInput() {
        String input = "Test Input\n";
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));

        String result = StudentManagementSystemIII.returnInput("Enter input: ");
        assertEquals("Test Input", result);
    }

    @Test
    public void studentID() {
        String input = "w1234567\n";
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));

        String result = StudentManagementSystemIII.studentID();
        assertEquals("w1234567", result);
    }

    @Test
    public void studentIndex() {
        String[][] array = {
                {"w1234567", "John Doe", "2023-01-01"},
                {"w7654321", "Jane Smith", "2023-02-01"},
                {"-", "-", "-"}
        };

        assertEquals(0, StudentManagementSystemIII.studentIndex("w1234567", array));
        assertEquals(1, StudentManagementSystemIII.studentIndex("w7654321", array));
        assertEquals(-1, StudentManagementSystemIII.studentIndex("w0000000", array));
    }

    @Test
    public void displayDetails() {
        String[][] array = {{"w1234567", "John Doe", "2023-01-01"}};
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        StudentManagementSystemIII.displayDetails(0, array);

        String expectedOutput = "\nStudent Name    : John Doe\n" +
                "Student ID      : w1234567\n" +
                "Registered Date : 2023-01-01\n\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void returnYorN() {
        String input = "Y\n";
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));

        boolean result = StudentManagementSystemIII.returnYorN("Enter Y or N: ");
        assertTrue(result);

        input = "N\n";
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));

        result = StudentManagementSystemIII.returnYorN("Enter Y or N: ");
        assertFalse(result);
    }

    @Test
    public void emptyDataBase() {
        String[][] emptyArray = {{"-", "-", "-"}, {"-", "-", "-"}};
        assertTrue(StudentManagementSystemIII.emptyDataBase(emptyArray));

        String[][] nonEmptyArray = {{"-", "-", "-"}, {"w1234567", "John Doe", "2023-01-01"}};
        assertFalse(StudentManagementSystemIII.emptyDataBase(nonEmptyArray));
    }

    @Test
    public void initializeModuleEvaluator() {
        Student[] students = new Student[3];
        StudentManagementSystemIII.initializeModuleEvaluator(students);

        for (Student student : students) {
            assertNotNull(student);
            assertEquals("-", student.getStName());
            assertEquals("-", student.getStID());
        }
    }
}
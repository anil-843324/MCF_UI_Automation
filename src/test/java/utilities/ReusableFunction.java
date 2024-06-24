package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.UUID;


import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.awt.Robot;

public class ReusableFunction {

    public static int[] parseDate(String dateString) {
        String[] dateParts = dateString.split("/");
        int month = Integer.parseInt(dateParts[0]);
        int day = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);
        return new int[] { month, day, year };
    }

    public static String generateRandomEmail() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10) + "@yopmail.com";
    }

    public static String generateRandomName(String name) {
        String uuidString = UUID.randomUUID().toString().replaceAll("-", "");
        String alphaString = uuidString.replaceAll("[0-9]", "");
        return name + alphaString.substring(0, Math.min(alphaString.length(), 10));
    }

    public static String removeSpaces(String input) {
        return input.replaceAll(" ", "");
    }

    public static void openExcelFile(String excelPath) {
        // excel = new ExcelReader(excelPath);

        try {

            // Create a File object
            File file = new File(excelPath);

            // Check if the file exists
            if (file.exists()) {
                // Open the file with the default program associated with Excel files
                Desktop.getDesktop().open(file);

                // Simulate Ctrl+S to save the file
                Robot robot = new Robot();
                robot.delay(1000); // Delay to ensure the file is fully opened
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_S);
                robot.keyRelease(KeyEvent.VK_S);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Simulate Alt+F4 to close the file
                robot.keyPress(KeyEvent.VK_ALT);
                robot.keyPress(KeyEvent.VK_F4);
                robot.keyRelease(KeyEvent.VK_F4);
                robot.keyRelease(KeyEvent.VK_ALT);

            } else {
                System.out.println("File does not exist.");
            }
        } catch (IOException | java.awt.AWTException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

public static void printData(Hashtable<String, String> data) {
        System.out.println(
                data.get("LoanPurposeOther") + " " + data.get("Title") + ", " +
                data.get("Forename") + ", " + data.get("Surname") + ", " +
                parseDate(data.get("Day(DOB)")) + "/" + 
                parseDate(data.get("Month(DOB)")) + "/" + 
                parseDate(data.get("Year(DOB)")) + ", " +
                data.get("Email") + ", " + data.get("Phone_Number") + ", " +
                data.get("Postcode") + ", " + data.get("FullAddress") + ", " +
                data.get("MoveIn") + ", " + data.get("PrevPostcode") + ", " +
                data.get("PrevFullAddress") + ", " + data.get("CurrentIncome") + ", " +
                data.get("Dependent") + ", " + data.get("Residential Status") + ", " +
                data.get("RentOrMortgage") + ", " + data.get("EmploymentStatus") + ", " +
                data.get("Employment_Sector") + ", " + data.get("Employer")
        );
    }
    
}

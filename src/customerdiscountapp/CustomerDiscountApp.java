/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package customerdiscountapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
* Customer Discount Application
*  
* This program reads customer data from "customers.txt", validates it, 
* calculates the final value with discounts based on the customer's class, 
* and saves the output to "customerdiscount.txt".
*  
* Input file format:
* Line 1 – <First Name> <Second Name>
* Line 2 – <Total Purchase>
* Line 3 – <Class>
* Line 4 – <Last Purchase>
*  
* Output file format:
* <First name> - <Second Name>
* <Final Value>
 
*Discount criteria is based on the customer class and last purchase year.
*  
* @author tiago
*/
public class CustomerDiscountApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // INPUTS: Initialize file paths for reading customer data and writing results
        String filePath = "customers.txt";  // Path to input file
        String outFilePath = "customerdiscount.txt";  // Path to output file

        // Processing: Open files and initialize readers/writers with try-with-resources to ensure closure
        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
             BufferedWriter bwriter = new BufferedWriter(new FileWriter(outFilePath))) {

            String line; // Temporary holder for each line of data

            // INPUTS & PROCESSING: Loop to continue reading the file until it is null (last row)
            while ((line = br.readLine()) != null) {
                try {
                    // Reading and assigning customer details from the file and separate each piece of information//
                    String fullName = line;  // Full name (First and Second Name in one line)
                    double purchaseAmount = Double.parseDouble(br.readLine());  // Total Purchase
                    int customerClass = Integer.parseInt(br.readLine());  // Customer Class
                    int purchaseYear = Integer.parseInt(br.readLine());  // Last Purchase Year

                    // Splitting full name into first and last name
                    String[] names = fullName.split(" ");
                    if (names.length != 2) {
                        System.out.println("Error: Invalid name format for " + fullName);
                        continue;  // Skip to the next iteration of the loop if data is invalid
                    }

                    String firstName = names[0];
                    String lastName = names[1];

                    // Validate the first name to ensure it only contains letters
                    if (!firstName.matches("[a-zA-Z]+")) {
                        System.out.println("Error: Invalid first name for " + fullName);
                        continue;  // Skip to the next interation of the loop if first name is invalid
                    }

                    // Validate the customer class to ensure it is within allowed range (1-3)
                    if (customerClass < 1 || customerClass > 3) {
                        System.out.println("Error: Customer Class must be between 1 and 3 for " + fullName);
                        continue;  // Skip to the next interation of the loop if class is invalid
                    }

                    // Validate the purchase year to ensure it falls within the required range (2000-2024)
                    if (purchaseYear < 2000 || purchaseYear > 2024) {
                        System.out.println("Error: Invalid purchase year for " + fullName);
                        continue;  // Skip to the next interation of the loop if year is invalid
                    }

                    // Processing: Creating an object cstr - connection with my class Customers and calculate the final value after discount
                    Customers cstr = new Customers(firstName, lastName, purchaseAmount, customerClass, purchaseYear);
                    double finalValue = cstr.calculateFinalValue();  // Calculate final value based on discount criteria

                    // OUTPUTS: Write the result to the output file in the specified format
                    bwriter.write(firstName + " - " + lastName);
                    bwriter.newLine();  // Move to the next line for final value
                    bwriter.write(Double.toString(finalValue));
                    bwriter.newLine();  // Blank line to separate customers as requested

                    // Print the output to the console for immediate feedback
                    System.out.println(cstr.getFirstName() + " " + cstr.getLastName());
                    System.out.println("Final Value: " + finalValue);

                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid data format. Skipping entry.");  // Error message for invalid number formats
                }
            }

            // OUTPUTS: Confirmation message indicating that processing is complete
            System.out.println("Processing complete. Results saved to " + outFilePath);

        } catch (IOException e) {
            e.printStackTrace();  // Handle file-related exceptions
        }
    }
}

//test
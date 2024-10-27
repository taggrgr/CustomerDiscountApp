/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customerdiscountapp;

/**
 * Represents a customer with their personal and purchase details, 
 * used to calculate the final purchase value after applying discounts.
 * 
 * @author tiago
 */

public class Customers {
    private String firstName;      // Customer's first name
    private String lastName;       // Customer's last name
    private double purchaseAmount; // Total purchase amount
    private int customerClass;     // Customer classification (1-3)
    private int purchaseYear;      // Year of the customer's last purchase

    // Constructor to initialize the Customers object
    public Customers(String firstName, String lastName, double purchaseAmount, int customerClass, int purchaseYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.purchaseAmount = purchaseAmount;
        this.customerClass = customerClass;
        this.purchaseYear = purchaseYear;
    }

    // Getters for accessing private fields
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getPurchaseAmount() {
        return purchaseAmount;
    }

    public int getCustomerClass() {
        return customerClass;
    }

    public int getPurchaseYear() {
        return purchaseYear;
    }

    // Method to calculate the final value after applying discounts
    public double calculateFinalValue() {
        double discount = 0;    // Initialize discount rate
        int currentYear = 2024; // Assuming current year is 2024

        // Determine discount based on customer class and purchase year
        switch (customerClass) {
            case 1:
                if (purchaseYear == currentYear) {
                    discount = 0.30;  // 30% discount
                } else if (purchaseYear >= currentYear - 5) {
                    discount = 0.20;  // 20% discount
                } else {
                    discount = 0.10;  // 10% discount
                }
                break;
            case 2:
                if (purchaseYear == currentYear) {
                    discount = 0.15;  // 15% discount
                } else if (purchaseYear >= currentYear - 5) {
                    discount = 0.13;  // 13% discount
                } else {
                    discount = 0.05;  // 5% discount
                }
                break;
            case 3:
                if (purchaseYear == currentYear) {
                    discount = 0.03;  // 3% discount
                } else {
                    discount = 0.00;  // No discount
                }
                break;
            default:
                System.out.println("Invalid customer class.");
                break;
        }

        return purchaseAmount - (purchaseAmount * discount);
    }
}

//test
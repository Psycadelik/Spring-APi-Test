package com.salesapi.demo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TestScanner {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) {
            testingException();
    }

    private static void testingException() {

        System.out.println("Enter the value of x");


        try {
            int x;
            x = scanner.nextInt();
            System.out.println("The value of x is : " + x);

        } catch (InputMismatchException ex) {
            System.out.println("Invalid input");
//            ex.printStackTrace();
        }


    }


}

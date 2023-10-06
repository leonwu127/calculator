package leon.home.jagex;

import leon.home.jagex.solution.*;

import java.util.Scanner;

public class CalculatorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("------------------------------------");
            System.out.println("Choose a calculator:");
            System.out.println("1. Calculator for Question 1");
            System.out.println("2. Calculator for Question 2");
            System.out.println("3. Calculator for Question 3");
            System.out.println("4. Hexadecimal Calculator");
            System.out.println("5. Advanced Calculator");
            System.out.println("6. Exit");

            int c = scanner.nextInt();
            scanner.nextLine();

            Calculator calculator;
            String expression;

            try {
                switch (c) {
                    case 1:
                        System.out.println("You have chosen Calculator for Question 1");
                        calculator = new Calculator1();
                        System.out.println("Enter an expression:");
                        expression = scanner.nextLine();
                        System.out.println("Result: " + calculator.calculate(expression));
                        System.out.println();
                        continue;
                    case 2:
                        System.out.println("You have chosen Calculator for Question 2");
                        calculator = new Calculator2();
                        System.out.println("Enter an expression:");
                        expression = scanner.nextLine();
                        System.out.println("Result: " + calculator.calculate(expression));
                        System.out.println();
                        continue;
                    case 3:
                        System.out.println("You have chosen Calculator for Question 3");
                        calculator = new Calculator3();
                        System.out.println("Enter an expression:");
                        expression = scanner.nextLine();
                        System.out.println("Result: " + calculator.calculate(expression));
                        System.out.println();
                        continue;
                    case 4:
                        displayHexCalculatorFeatures();
                        calculator = new HexadecimalCalculator();
                        System.out.println("Enter an expression:");
                        expression = scanner.nextLine();
                        System.out.println("Result: " + calculator.calculate(expression));
                        System.out.println();
                        continue;
                    case 5:
                        displayAdvancedCalculatorFeature();
                        System.out.println("Enter a number to set the decimal point scale (default is 3):");
                        int scale = scanner.nextInt();
                        scanner.nextLine();
                        calculator = new AdvancedCalculator(scale);
                        System.out.println("Enter an expression:");
                        String expression2 = scanner.nextLine();
                        System.out.println("Result: " + calculator.calculate(expression2));
                        System.out.println();
                        continue;
                    case 6:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid expression. Try again.");
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void displayHexCalculatorFeatures() {
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("You have chosen Hexadecimal Calculator");
        System.out.println("This Calculator support the following features:");
        System.out.println("1. Support for hexadecimal numbers long integers only");
        System.out.println("2. Support for negative numbers");
        System.out.println("3. Support for +, -, *, /, ^ operators");
        System.out.println("4. Hex number should be prefixed with 0x");
        System.out.println("-----------------------------------------------------------------------");
    }

    private static void displayAdvancedCalculatorFeature() {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("You have chosen Advanced Calculator");
        System.out.println("This Calculator support the following features:");
        System.out.println("1. Support for decimal numbers scaled to n decimal places:");
        System.out.println("2. Support for negative numbers");
        System.out.println("3. Support for +, -, *, /, ^ operators");
        System.out.println("4. Support for parentheses");
        System.out.println("5. Support for sin(), cos(), tan(), log() functions");
        System.out.println("6. Support for factorial (!) operator, which considered as a function");
        System.out.println("----------------------------------------------------------------------");
    }
}

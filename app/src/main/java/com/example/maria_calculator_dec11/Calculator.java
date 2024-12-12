package com.example.maria_calculator_dec11;

import java.util.ArrayList;

public class Calculator {
    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    // Check precedence of operators
    public static boolean hasPrecedence(char op1, char op2) {
        if ((op1 == '+' || op1 == '-') && (op2 == '*' || op2 == '/')) {
            return false;
        }
        return true;
    }

    // Check if the string is a number
    public static boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Perform the calculation based on the operator
    public static double calculateResult(double n1, double n2, char op) {
        switch (op) {
            case '+': return n1 + n2;
            case '-': return n1 - n2;
            case '*': return n1 * n2;
            case '/': return n2 != 0 ? n1 / n2 : 0;
            default: return 0;
        }
    }

    // Evaluate the expression
    public static String calculation(String input) {
        if (input.isEmpty()) return "";

        String[] tokens = input.split(" ");
        ArrayList<Double> numbers = new ArrayList<>();
        ArrayList<Character> operators = new ArrayList<>();

        for (String token : tokens) {
            if (isNumber(token)) {
                numbers.add(Double.parseDouble(token));
            } else if (isOperator(token.charAt(0))) {
                while (!operators.isEmpty() && hasPrecedence(token.charAt(0), operators.get(operators.size() - 1))) {
                    double b = numbers.remove(numbers.size() - 1);
                    double a = numbers.remove(numbers.size() - 1);
                    char op = operators.remove(operators.size() - 1);
                    numbers.add(calculateResult(a, b, op));
                }
                operators.add(token.charAt(0));
            }
        }

        while (!operators.isEmpty()) {
            double b = numbers.remove(numbers.size() - 1);
            double a = numbers.remove(numbers.size() - 1);
            char op = operators.remove(operators.size() - 1);
            numbers.add(calculateResult(a, b, op));
        }

        return numbers.get(0).toString();
    }
}

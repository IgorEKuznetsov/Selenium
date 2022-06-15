package com.auto.main.lesson4;

public class Triangle {

    public static void main(String[] args) {
        System.out.println(triangleArea(7, 3, 5));
    }

    public static double triangleArea(double a, double b, double c) {
        double p = (a + b + c) / 2;
        if (a >= (b + c) || b >= (c + a) || c >= (a + b) || a <= 0 || b <= 0 || c <= 0) {
            throw new WrongInputException();
        }
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }


}

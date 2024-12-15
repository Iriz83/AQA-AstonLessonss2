package org.example;

public class Main {
    public static void main(String[] args) {
        int number = 5; // Число, для которого вычисляем факториал
        long result = Factorial.calculateFactorial(number); // Вызов метода из класса Factorial

        // Вывод результата в консоль
        System.out.println("Факториал числа " + number + " = " + result);
    }
}
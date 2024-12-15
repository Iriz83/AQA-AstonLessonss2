package org.example;

public class Factorial {
    // Метод для вычисления факториала числа
    public static long calculateFactorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Факториал для отрицательных чисел не определён.");
        }
        long result = 1; // Инициализируем результат как 1
        for (int i = 1; i <= n; i++) {
            result *= i; // Умножаем результат на текущее значение i
        }
        return result;
    }

}

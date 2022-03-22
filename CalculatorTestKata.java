package CalculatorTestKata;

import java.util.Scanner;

public class CalculatorTestKata {
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);
        System.out.println(main.myCalc(scanner.nextLine()));
    }
}

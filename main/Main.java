package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import analyze.Analysis;
import analyze.Support;
import exceptions.NumberTypeMismatchException;
import exceptions.OutOfNumberFormatException;
import exceptions.WrongTypeException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        Support.print();
        do {
            input = buffer.readLine();
            input.trim();
            
            try {
                if (input.equalsIgnoreCase("quit")) {
                    System.out.println("Подготовка к завершение программы.");
                    for (int i = 5; i > 0; i--) {
                        Thread.sleep(500);
                        System.out.println(i);
                    }
                } else {
                    Analysis analysis = new Analysis(input.toUpperCase());
                    int result = 0;
                    if (analysis.isMatch()) {
                        result = analysis.getData().compute();
                    } else {
                         throw new WrongTypeException("Выражение вида: " + analysis.getInput() + " не является числовым либо удовлетворяющим условиям ввода.");
                    }
                    System.out.println("Результат вычисления: " + result);
                }
            } catch (WrongTypeException ex) {
                System.out.println("WrongTypeException: ");
                ex.printStackTrace();
                System.exit(1);
            } catch (OutOfNumberFormatException ex) {
                System.out.println("OutOfNumberFormatException: ");
                ex.printStackTrace();
                System.exit(2);
            } catch (NumberTypeMismatchException ex) {
                System.out.println("NumberTypeMismatchException: ");
                ex.printStackTrace();
                System.exit(3);
            } catch (SecurityException ex) {
                ex.printStackTrace();
            }
        } while (!input.equals("quit"));
        try {
            buffer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Завершение программы.");
    }

}

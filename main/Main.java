package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.file.Paths;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import analyze.Analysis;
import analyze.Support;
import exceptions.NumberTypeMismatchException;
import exceptions.OutOfNumberFormatException;
import exceptions.WrongTypeException;

public class Main {
    static {
        try {
            new DOMConfigurator().doConfigure(Paths.get("context\\log4j.xml").toUri().toURL(), LogManager.getLoggerRepository());
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }
    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        Support.print();
        do {
            input = buffer.readLine();
            input.trim();
            logger.info(input);
            
            try {
                if (input.equalsIgnoreCase("quit")) {
                    System.out.println("Подготовка к завершение программы.");
                    for (int i = 5; i > 0; i--) {
                        Thread.sleep(500);
                        System.out.println(i);
                    }
                } else {
                    Analysis analysis = new Analysis(logger, input.toUpperCase());
                    int result = 0;
                    if (analysis.isMatch()) {
                        result = analysis.getData().compute();
                        logger.info("match: " + analysis.getInput());
                    } else {
                        logger.info("no match: " + analysis.getInput());
                        throw new WrongTypeException("Выражение вида: " + analysis.getInput() + " не является числовым либо удовлетворяющим условиям ввода.");
                    }
                    System.out.println("Результат вычисления: " + result);
                }
            } catch (WrongTypeException ex) {
                logger.error("WrongTypeException: ", ex);
                System.exit(1);
            } catch (OutOfNumberFormatException ex) {
                logger.error("OutOfNumberFormatException: ", ex);
                System.exit(2);
            } catch (NumberTypeMismatchException ex) {
                logger.error("NumberTypeMismatchException: ", ex);
                System.exit(3);
            } catch (SecurityException ex) {
                logger.error("SecurityException: ", ex);
            }
        } while (!input.equals("quit"));
        try {
            buffer.close();
        } catch (IOException ex) {
            logger.error("IOException while trying to close buffer: ", ex);;
        }
        System.out.println("Завершение программы.");
    }

}

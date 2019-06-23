package analyze;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import calculate.Calculation;
import calculate.Data;
import calculate.Operations;
import exceptions.NumberTypeMismatchException;
import exceptions.OutOfNumberFormatException;

public class Analysis implements BasicAnalysis {
    final private String regex1= "\\p{Digit}{1,2}[+-/*]{1}\\p{Digit}{1,2}";
    final private String regex2 = "(\\p{Space})+";
    final private String regex3 = "[XVI]+[+-/*]{1}[XVI]+";
    final private String regex4 = "[+-/*]{1}";
    final private String regex5 = "[XVI]+[+-/*]{1}(\\p{Digit}){1,2}";
    final private String regex6 = "\\p{Digit}{1,2}[+-/*]{1}[XVI]+";
    private String input;
    private Logger logger;
    private TypeNumbers typeNumbers;
    
    public Analysis() {
        
    }
    
    public Analysis(Logger logger, String input) {
        this.setLogger(logger);
        this.setInput(input);
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        Pattern pattern1 = Pattern.compile(regex2);
        String[] elements = pattern1.split(input);
        String result = "";
        for (String element: elements) {
            result += element;
        }
        logger.info("result: " + result);
        this.input = result;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    
    @Override
    public boolean isMatch() throws NumberTypeMismatchException {
        Pattern pattern1 = Pattern.compile(regex1);
        Matcher matcher1 = pattern1.matcher(input);
        Pattern pattern2 = Pattern.compile(regex3);
        Matcher matcher2 = pattern2.matcher(input);
        Pattern pattern5 = Pattern.compile(regex5);
        Matcher matcher5 = pattern5.matcher(input);
        Pattern pattern6 = Pattern.compile(regex6);
        Matcher matcher6 = pattern6.matcher(input);
        if (matcher1.matches()) {
            typeNumbers = TypeNumbers.ARABIC;
            return true;
        } else if (matcher2.matches()) {
            typeNumbers = TypeNumbers.ROMAN;
            return true;
        } else if (matcher5.matches()) {
            throw new NumberTypeMismatchException("Выражение вида \"VII + 4\" не доступно для обработки.");
        } else if (matcher6.matches()) {
            throw new NumberTypeMismatchException("Выражение вида \"4 + VII\" не доступно для обработки.");
        } else {
            return false;
        }
    }

    @Override
    public Calculation getData() throws OutOfNumberFormatException {
        Calculation data;
        if (typeNumbers.compareTo(TypeNumbers.ARABIC) == 0) {
            Pattern pattern = Pattern.compile(regex4);
            String[] elements = pattern.split(input);
            int[] values = new int[elements.length];
            for (int i = 0; i < values.length; i++) {
                values[i] = Integer.parseInt(elements[i]);
                if (Support.isOutFormat(values[i])) {
                    throw new OutOfNumberFormatException((i + 1) + "-е значение не входит в диапазон от 1 до 10.");
                }
            }
            Matcher matcher = pattern.matcher(input);
            String temp = "";
            if (matcher.find()) {
                temp = matcher.group();
            } else {
                logger.info("Operation character is missing.");
            }
            Operations operation = Support.getOperation(temp);
            data = new Data(operation, values[0], values[1]);
        } else if (typeNumbers.compareTo(TypeNumbers.ROMAN) == 0) {
            Pattern pattern = Pattern.compile(regex4);
            String[] elements = pattern.split(input);
            int[] values = new int[elements.length];
            for (int i = 0; i < values.length; i++) {
                values[i] = Support.getValue(elements[i]);
                if (Support.isOutFormat(values[i])) {
                    throw new OutOfNumberFormatException((i + 1) + "-е значение не входит в диапазон от 1 до 10.");
                }
            }
            Matcher matcher = pattern.matcher(input);
            String temp = "";
            if (matcher.find()) {
                temp = matcher.group();
            } else {
                logger.info("Operation character is missing.");
            }
            Operations operation = Support.getOperation(temp);
            data = new Data(operation, values[0], values[1]);
        } else {
            return null;
        }
        return data;
    }
    
}

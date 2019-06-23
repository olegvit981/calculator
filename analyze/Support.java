package analyze;

import calculate.Operations;

public class Support {
    
    public static Operations getOperation(String input) {
        if (input.equals("+")) {
            return Operations.ADDITION;
        } else if (input.equals("-")) {
            return Operations.SUBTRACTION;
        } else if (input.equals("*")) {
            return Operations.MULTIPLICATION;
        } else {
            return Operations.DIVISION;
        }
    }
    
    public static int getValue(String romeNumber) {
        int value = 0;
        switch (romeNumber) {
        case "I":
            value = 1;
            break;
        case "II":
            value = 2;
            break;
        case "III":
            value = 3;
            break;
        case "IV":
            value = 4;
            break;
        case "V":
            value = 5;
            break;
        case "VI":
            value = 6;
            break;
        case "VII":
            value = 7;
            break;
        case "VIII":
            value = 8;
            break;
        case "IX":
            value = 9;
            break;
        case "X":
            value = 10;
            break;
        default:
            value = 0;
            break;
        }
        return value;
    }
    
    public static boolean isOutFormat(int value) {
        if (value>0 & value<=10) {
            return false;
        } else {
            return true;
        }
    }
    
    public static void print() {
        System.out.println("Введите математическое действие в виде \"1 + 1\", используйте \'quit\' для выхода.");
        System.out.println("Калькулятор выполняет операции сложения \"+\", вычитания \"-\",");
        System.out.println("умножения \"*\", деления \"/\".");
        System.out.println("Операции выполняются над целыми числами от 1 до 10.");
        System.out.println("Допускается вводить римские цифры: I, II, III, IV, V, VI, VII, VIII, IX, X.");
        System.out.println("Одновременный ввод римских и арабских цифр в виде \"5 + V\" не допускается.");
    }

}

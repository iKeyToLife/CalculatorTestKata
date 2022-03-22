package CalculatorTestKata;

import java.util.Arrays;

class Main {
    private String otvet;
    private String roman = "IIIVIIIX";
    private String arabian = "1023456789";
    private String[] romanArray = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

    private void checkForException(String[] arrayString) throws Exception {
        if (arrayString.length != 3) {
            throw new Exception("Введено неверное кол-во символов или неверный формат");
        }

        if ((roman.contains(arrayString[0]) && arabian.contains(arrayString[2]))
                || (arabian.contains(arrayString[0]) && roman.contains(arrayString[2]))) {
            throw new Exception("Данные могут быть только одного типа, Пример: римская + римская, (I+II)");
        }
    }


    public String myCalc(String line) throws Exception {
        String[] symbols = line.split("\\s+");
        checkForException(symbols);

        if (arabian.contains(symbols[0]) && arabian.contains(symbols[2])) {
            otvet = operationArab(symbols);
        } else if (roman.contains(symbols[0]) && roman.contains(symbols[2])) {
            otvet = operationRoman(symbols);
        } else {
            throw new Exception("Введены некорректные данные");
        }
        return otvet;
    }

    private String operationArab(String[] arabArray) throws Exception {
        int result;
        int first = Integer.parseInt(arabArray[0]);
        int second = Integer.parseInt(arabArray[2]);

        switch (arabArray[1]) {
            case "+":
                result = first + second;
                break;
            case "-":
                result = first - second;
                break;
            case "*":
                result = first * second;
                break;
            case "/":
                result = first / second;
                break;
            default:
                throw new Exception("Не правильно введен символ операции," +
                        "используйте только \"+\", \"-\", \"*\", \"/\"");
        }

        return String.valueOf(result);
    }

    private String operationRoman(String[] romanArray) throws Exception {
        int result;
        int first = Integer.parseInt(convertRomanToArab(romanArray[0]));
        int second = Integer.parseInt(convertRomanToArab(romanArray[2]));

        switch (romanArray[1]) {
            case "+":
                result = first + second;
                break;
            case "-":
                if (second > first) {
                    throw new Exception("Операции с римскими числами не могут быть меньше 1");
                } else {
                    result = first - second;
                    break;
                }
            case "*":
                result = first * second;
                break;
            case "/":
                result = first / second;
                break;
            default:
                throw new Exception("Не правильно введен символ операции," +
                        "используйте только \"+\", \"-\", \"*\", \"/\"");
        }
        return convertArabToRoman(result);
    }

    private String convertArabToRoman(int arab) {
        String[] o = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] t = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String tens = t[(arab % 100) / 10];
        String ones = o[arab % 10];
        return tens + ones;
    }

    private String convertRomanToArab(String roman) {
        String arab = "";
        for (String romanNumber : romanArray) {
            if (roman.equals(romanNumber)) {
                arab = "" + (Arrays.asList(romanArray).indexOf(romanNumber) + 1);
                break;
            }
        }
        return arab;
    }
}
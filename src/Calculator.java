import java.util.*;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите арифмитеческое выражение (например 1 + 1): ");
        String input = scanner.nextLine();

        String output = calc(input);
        System.out.println("Output: " + output);

        scanner.close();
    }

    public static String calc(String input) {
        String[] tokens = input.split(" ");
        if (tokens.length != 3) {
            throw new IllegalArgumentException("Ошибка: Формат математической операции не удовлетворяет заданию - два операнда и один оператор.");
        }

        String numStr1 = tokens[0];
        String operator = tokens[1];
        String numStr2 = tokens[2];

        boolean isRomanNumeral1 = isRomanNumeral(numStr1);
        boolean isRomanNumeral2 = isRomanNumeral(numStr2);

        if ((isRomanNumeral1 && !isRomanNumeral2) || (!isRomanNumeral1 && isRomanNumeral2)) {
            throw new IllegalArgumentException("Ошибка: Используются одновременно разные системы счисления.");
        }

        int num1, num2;

        if (isRomanNumeral1) {
            num1 = parseRomanNumeral(numStr1);
            num2 = parseRomanNumeral(numStr2);
        } else {
            num1 = parseInputNumber(numStr1);
            num2 = parseInputNumber(numStr2);
        }

        if (num1 <= 0 || num2 <= 0 || num1 > 10 || num2 > 10) {
            throw new IllegalArgumentException("Ошибка: Калькулятор принимает на вход числа от 1 до 10 включительно.");
        }

        int result;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("Ошибка: Недопустимый оператор: " + operator);
        }

        return isRomanNumeral1 ? intToRoman(result) : String.valueOf(Math.max(result, 0));
    }

    public static boolean isRomanNumeral(String str) {
        return str.matches("^(I|II|III|IV|V|VI|VII|VIII|IX|X)$");
    }

    public static int parseInputNumber(String numStr) {
        try {
            return Integer.parseInt(numStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Калькулятор умеет работать только с целыми числами: " + numStr);
        }
    }

    public static int parseRomanNumeral(String romanNumeral) {
        if (romanNumeral.equals("I")) return 1;
        if (romanNumeral.equals("II")) return 2;
        if (romanNumeral.equals("III")) return 3;
        if (romanNumeral.equals("IV")) return 4;
        if (romanNumeral.equals("V")) return 5;
        if (romanNumeral.equals("VI")) return 6;
        if (romanNumeral.equals("VII")) return 7;
        if (romanNumeral.equals("VIII")) return 8;
        if (romanNumeral.equals("IX")) return 9;
        if (romanNumeral.equals("X")) return 10;
        throw new IllegalArgumentException("Неверные римские цифры: " + romanNumeral);
    }

    public static String intToRoman(int num) {
        if (num < 1 || num > 100) {
            throw new IllegalArgumentException("Cannot convert " + num + " to Roman numeral.");
        }

        String[] romanNumerals = {
                "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
                "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
                "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };

        return romanNumerals[num - 1];
    }

    static String arabicToRoman(int arabic) {
        if (arabic < 1) {
            throw new IllegalArgumentException("Ошибка: Невозможно преобразовать отрицательные или нулевые значения в римские цифры.");
        }

        String[] romanNumerals = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};
        int[] values = {1, 4, 5, 9, 10, 40, 50, 90, 100};

        StringBuilder roman = new StringBuilder();

        for (int i = values.length - 1; i >= 0; i--) {
            while (arabic >= values[i]) {
                roman.append(romanNumerals[i]);
                arabic -= values[i];
            }
        }

        return roman.toString();
    }
}

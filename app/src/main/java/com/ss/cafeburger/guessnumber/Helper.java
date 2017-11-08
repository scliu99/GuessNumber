package com.ss.cafeburger.guessnumber;

/**
 * Created by cafeburger on 2017/11/8.
 */


public class Helper {
    public static String numberToString(int number) {
        String strNumber = "0000" + String.valueOf(number);
        int len = strNumber.length();
        strNumber = strNumber.substring(len - 4);
        return strNumber;
    }

    public static int[] initNumberArray() {
        int[] numbers = new int[10000];
        for (int i = 0; i <= 9999; i++) {
            numbers[i] = isSelfDup(i) ? 0 : i;
        }
        return numbers;
    }

    public static boolean isSelfDup(int number) {
        return checkDupB(number, number) > 0;
    }

    public static int checkDupA(int number1, int number2) {
        int result = 0;
        String strNumber1 = numberToString(number1);
        String strNumber2 = numberToString(number2);
        for (int i = 0; i < strNumber1.length(); i++) {
            if (strNumber1.substring(i, i + 1).equals(strNumber2.substring(i, i + 1))) {
                result++;
            }
        }
        return result;
    }

    public static int checkDupB(int number1, int number2) {
        int result = 0;
        int[] slot = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        String strNumber1 = numberToString(number1);
        String strNumber2 = numberToString(number2);
        for (int i = 0; i < strNumber1.length(); i++) {
            int digit1 = Integer.parseInt(strNumber1.substring(i, i + 1));
            int digit2 = Integer.parseInt(strNumber2.substring(i, i + 1));
            if (slot[digit1] == 0) {
                slot[digit1] = i + 1;
            } else {
                result++;
            }
            if (digit1 != digit2) {
                if (slot[digit2] == 0) {
                    slot[digit2] = i + 1;
                } else {
                    result++;
                }
            }
        }
        return result;
    }

    public static String checkDupAB(int number1, int number2) {
        return String.valueOf(checkDupA(number1, number2)) + String.valueOf(checkDupB(number1, number2));
    }

    public static int[] filterAB(int[] numbers, int number, String ab) {
        for (int i = 0; i < numbers.length; i++) {
            if (!checkDupAB(numbers[i], number).equals(ab)) {
                numbers[i] = 0;
            } else {
                System.out.print(numbers[i] + "\t");
            }
        }
        System.out.println();
        return numbers;
    }

    public static int guessNumber(int[] numbers) {
        int index = (int) (Math.random() * 10000);
        if (numbers[index] != 0) {
            return numbers[index];
        }
        // search backward
        for (int i = index - 1; i > 0; i--) {
            if (numbers[i] != 0) {
                return numbers[i];
            }
        }
        // search forward
        for (int i = index + 1; i <= 9999; i++) {
            if (numbers[i] != 0) {
                return numbers[i];
            }
        }
        return 0;
    }
}

package com.ss.cafeburger.guessnumber;

/**
 * Created by cafeburger on 2017/11/8.
 */


public class Helper {

    /**
     * 將數值陣列裡的所有數值設為i值(初始化),如果該數本身有任二個位數相同,則移除(設為0)
     *
     * @return
     */
    public static int[] initNumberArray() {
        int[] numbers = new int[10000];
        for (int i = 0; i <= 9999; i++) {
            numbers[i] = isSelfDup(i) ? 0 : i;
        }
        return numbers;
    }

    /**
     * 將數值轉成字串,未滿4位數,前面補0
     *
     * @param number 整數值
     * @return 轉換後的字串
     */
    public static String numberToString(int number) {
        String strNumber = "0000" + String.valueOf(number);
        int len = strNumber.length();
        strNumber = strNumber.substring(len - 4);
        return strNumber;
    }

    /**
     * 檢查數值中任何二位數是否有相同
     * @param number 檢查的數值
     * @return 若有任二位數相同則傳回true, 否則為false
     */
    public static boolean isSelfDup(int number) {
        return checkDupB(number, number) > 0;
    }

    /**
     * 比對二數值,看有幾個位數是"位置相同"且"數值相同" (簡稱A值)
     * @param number1 數值1
     * @param number2 數值2
     * @return "位置相同"且"數值相同"的個數
     */
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

    /**
     * 比對二數值,看有幾個位數是"位置不同"但"數值相同" (不包括位置相同者)(簡稱B值)
     * @param number1 數值1
     * @param number2 數值2
     * @return "位置不同"但"數值相同"的個數
     */
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

    /**
     * 比對二數值,檢查其AB值
     * @param number1 數值1
     * @param number2 數值2
     * @return A及B值(以字串型別回傳, 第1碼為A值, 第2碼為B值)
     */
    public static String checkDupAB(int number1, int number2) {
        return String.valueOf(checkDupA(number1, number2)) + String.valueOf(checkDupB(number1, number2));
    }


    /**
     * 將數值陣列中的每一個數值,與number比對,如果其AB值與ab值相同,則將之移除(設為0)
     * @param numbers 數值陣列(過濾前)
     * @param number 測試的數值
     * @param ab AB值
     * @return 過濾後的陣列
     */
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

    /**
     * 從數值陣列中挑一個非0數值出來,為了讓挑選的結果不規則,會由中數開始亂數挑選
     * @param numbers 數值陣列
     * @return 挑中的數值
     */
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

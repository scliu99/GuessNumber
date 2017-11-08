package com.ss.cafeburger.guessnumber;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "GuessNumber";

    private ListView listView;
    private Button restartButton;
    private List<String> numberList = new LinkedList<>();

    private static void startGame() {


        int[] numbers = Helper.initNumberArray();
        int computerGuessNumber = 0;
        String playerHintAB = "";

        while (true) {
            computerGuessNumber = Helper.guessNumber(numbers);
            if (computerGuessNumber == 0) {
                Log.i(TAG, "Your hint was incorrect!");
                break;
            }
            Log.i(TAG, "I guess your number is ");
            Log.i(TAG, Helper.numberToString(computerGuessNumber));
            Log.i(TAG, "Please give the hint (AB)");
            //playerHintAB = keyboard.nextLine();
            if (playerHintAB.equals("40")) {
                Log.i(TAG, "I win!");
                break;
            }
            Helper.filterAB(numbers, computerGuessNumber, playerHintAB);

        }
    }

    private static void test() {
        int n1 = 4239;
        int n2 = 1234;
        if (Helper.isSelfDup(n1) || Helper.isSelfDup(n2)) {
            Log.i(TAG, "n1 or n2 has duplicate digit");
            return;
        }
        int[] numbers = Helper.initNumberArray();
        numbers = Helper.filterAB(numbers, 1234, "11");
        for (int i = 0; i < numbers.length; i++) {
            Log.i(TAG, numbers[i] + "");
        }

        Log.i(TAG, Helper.checkDupA(n1, n2) + "");
        Log.i(TAG, Helper.checkDupB(n1, n2) + "");
        Log.i(TAG, Helper.checkDupAB(n1, n2) + "");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lvList);
        restartButton = findViewById(R.id.btnRestart);

        List<Item> items = new ArrayList<>();
        Item item1 = new Item("1234");
        items.add(item1);
        items.add(item1);
        items.add(item1);
        items.add(item1);
        items.add(item1);
        items.add(item1);
        items.add(item1);
        items.add(item1);
        items.add(item1);
        items.add(item1);
        items.add(item1);
        items.add(item1);
        items.add(item1);
        items.add(item1);
        items.add(item1);
        items.add(item1);
        MyListAdapter adapter = new MyListAdapter(this, items);
        listView.setAdapter(adapter);

        test();
    }
}

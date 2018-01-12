package com.ss.cafeburger.guessnumber;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "GuessNumber";

    private ListView listView;
    private Button guessButton;
    private List<String> numberList = new LinkedList<>();

    private List<Item> items = new ArrayList<>();
    private MyListAdapter adapter;

    private int[] numbers;
    private int computerGuessNumber = 0;

    // 測試用
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

    // 測試用
    private static void startGame2() {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lvList);
        guessButton = findViewById(R.id.btnGuess);
        guessButton.setText("猜測");
        guessButton.setOnClickListener(this);

        adapter = new MyListAdapter(this, items);
        listView.setAdapter(adapter);

        initGame();
        startGame();
        //test();
    }

    // 初始化
    private void initGame() {
        numbers = Helper.initNumberArray();
    }

    // 開始(或是下一步)
    private void startGame() {

        // 先取得一個由電腦猜測的4位數
        computerGuessNumber = Helper.guessNumber(numbers);
        // 如果取到的是0,代表沒有數值可取,主要原因是過程中有提示錯誤
        if (computerGuessNumber == 0) {
            Log.i(TAG, "Your hint was incorrect!");
            guessButton.setText("重玩");
        }
        // 將取得的數值放入Item物件中,Items是與adapter綁定的串列
        Item item = new Item(Helper.numberToString(computerGuessNumber));
        items.add(item);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        if (guessButton.getText().equals("重玩")) {
            guessButton.setText("猜測");
            items.clear();
            initGame();
            startGame();
            return;
        }
        String spA = items.get(items.size() - 1).getSpA();
        String spB = items.get(items.size() - 1).getSpB();
        String playerHintAB = spA + spB;
        Log.d(TAG, "playerHintAB=" + playerHintAB);

        if (playerHintAB.equals("40")) { // 如果提示為40,代表猜中了,結束!
            guessButton.setText("重玩");
        } else {
            // 將玩家的提示代入,取得下一個猜測數值
            Helper.filterAB(numbers, computerGuessNumber, playerHintAB);
            startGame(); // 跑下一個猜測
        }
    }
}

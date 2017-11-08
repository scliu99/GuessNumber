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

    private void initGame() {
        numbers = Helper.initNumberArray();

    }

    private void startGame() {

        computerGuessNumber = Helper.guessNumber(numbers);
        if (computerGuessNumber == 0) {
            Log.i(TAG, "Your hint was incorrect!");
            guessButton.setText("重玩");
        }
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
        if (playerHintAB.equals("40")) {
            guessButton.setText("重玩");
        } else {
            Helper.filterAB(numbers, computerGuessNumber, playerHintAB);
            startGame();
        }
    }
}

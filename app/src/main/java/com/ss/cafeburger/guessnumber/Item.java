package com.ss.cafeburger.guessnumber;

/**
 * Created by cafeburger on 2017/11/8.
 */

public class Item {


    private String number;
    private String spA = "0";
    private String spB = "0";

    public Item(String number) {
        this.number = number;
        //this.itemDescription = description;
    }

    public String getSpA() {
        return spA;
    }

    public void setSpA(String spA) {
        this.spA = spA;
    }

    public String getSpB() {
        return spB;
    }

    public void setSpB(String spB) {
        this.spB = spB;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}

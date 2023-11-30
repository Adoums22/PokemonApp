package com.amonteiro.a2023_11_sopra;

public class DiceBean {

    private int value = 1;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (value < 1) {
            this.value = 1;
        }
        else if (value > 6) {
            this.value = 6;
        }
        else {
            this.value = value;
        }
    }


}


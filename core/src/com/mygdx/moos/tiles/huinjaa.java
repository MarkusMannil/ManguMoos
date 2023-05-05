package com.mygdx.moos.tiles;

import java.util.ArrayList;
import java.util.Arrays;

public class huinjaa {

    public static void main(String[] args) {
        int x = 0, y = 0;
        for (int i = -10; i < 10; i++) {
            for (int j = -10; j < 10; j++) {
                System.out.println(Math.atan2(y - i, x - j) / (2* Math.PI));
            }
        }


    }


}

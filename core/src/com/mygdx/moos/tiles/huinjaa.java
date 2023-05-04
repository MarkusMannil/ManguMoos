package com.mygdx.moos.tiles;

import java.util.ArrayList;
import java.util.Arrays;

public class huinjaa {

    public static void main(String[] args) {

        ArrayList<Integer> boat = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            boat.add(i);
        }

        for (int i = boat.size() - 1; i > 0; i--) {
            System.out.println(boat);
            boat.remove(boat.get(i));
        }
        System.out.println(boat);

    }



}

package com.zone.toy.simulator.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ToyUtils {

    public static final String MOVE = "MOVE";
    public static final String LEFT = "LEFT";
    public static final String RIGHT = "RIGHT";
    public static final String PLACE = "PLACE";
    public static final String COMMA = ",";
    public static final int table_length = 4;

    public static ArrayList<String> readCommands() {
        ArrayList<String> seqofCommands = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String[] tokens = scanner.nextLine().split("\n");
            seqofCommands.add(tokens[0]);
            if (Arrays.toString(tokens).contains("REPORT")) {

                break;
            }
        }
        scanner.close();
        return seqofCommands;
    }

    public static boolean validateNumber(int n) {
        return (n >= 0) && n <= table_length;
    }

    public static String readString() {
        String returnStr = "";
        try {
            BufferedReader bufReader = new BufferedReader(
                    new InputStreamReader(System.in));
            returnStr = bufReader.readLine();
        } catch (IOException e) {
            System.out.println("IOException is :" + e);
        }
        return returnStr;
    }
}

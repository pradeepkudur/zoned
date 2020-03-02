package com.zone.toy.simulator.application;

import com.zone.toy.simulator.model.Direction;
import com.zone.toy.simulator.model.InstructionType;
import com.zone.toy.simulator.utils.ToyUtils;

import java.util.ArrayList;
import java.util.Arrays;

import static com.zone.toy.simulator.utils.ToyUtils.validateNumber;


public class MoveToy {


    static boolean initialPlaceCmd = true;
    static String position = null;

    private static void callPlace() {
        System.out.println("Place the toy on the table using place command for eg: PLACE 0,0,NORTH( x and y cordinates should between 0-5)");
        String command = ToyUtils.readString();
        placeToy(command);

    }

    private static void reportDirection() {
        if (!position.isEmpty() && position != null) {
            System.out.println("Output :" + position);
        }
    }

    private static void turnRight() {
        String[] currentPos = position.split(ToyUtils.COMMA);
        String facing = currentPos[2];
        if (currentPos[2].equalsIgnoreCase(Direction.WEST.name())) {
            position = currentPos[0] + ToyUtils.COMMA + currentPos[1] + ToyUtils.COMMA+Direction.NORTH.name();
        } else {
            String changedFace = Direction.values()[Direction.valueOf(currentPos[2]).ordinal() + 1].name();
            position = currentPos[0] + ToyUtils.COMMA + currentPos[1] + ToyUtils.COMMA + changedFace;
        }
    }

    private static void turnLeft() {
        String[] currentPos = position.split(ToyUtils.COMMA);
        String facing = currentPos[2];
        if (currentPos[2].equalsIgnoreCase(Direction.NORTH.name())) {
            position = currentPos[0] + ToyUtils.COMMA + currentPos[1] + ToyUtils.COMMA+Direction.WEST.name();
        } else {
            String changedFace = Direction.values()[Direction.valueOf(currentPos[2]).ordinal() - 1].name();
            position = currentPos[0] + ToyUtils.COMMA + currentPos[1] + ToyUtils.COMMA + changedFace;
        }
    }

    private static void moveToy() {

        String[] currentPos = position.split(ToyUtils.COMMA);
        String facing = currentPos[2];
        int x = Integer.parseInt(currentPos[0]);
        int y = Integer.parseInt(currentPos[1]);
        switch (facing) {

            case "NORTH":
                changePos(0, 1, x, y, facing);
                break;
            case "SOUTH":
                changePos(0, -1, x, y, facing);
                break;
            case "EAST":
                changePos(1, 0, x, y, facing);
                break;
            case "WEST":
                changePos(-1, 0, x, y, facing);
                break;
        }
    }

    private static void changePos(int i, int j, int currentx, int currenty, String face) {
        int x = currentx + i;
        int y = currenty + j;
        if (validateNumber(x) && validateNumber(y)) {
            position = x + ToyUtils.COMMA + y + ToyUtils.COMMA + face;

        }
    }

    private static void placeToy(String command) {
        try {
            String[] cmdline = command.split("\\s+");
            String keyword = cmdline[0];
            if (cmdline.length > 1) {
                String[] values = cmdline[1].split(ToyUtils.COMMA);
                if (values.length==3){
                    int x = Integer.parseInt(values[0].trim());
                    int y = Integer.parseInt(values[1].trim());
                    String face = values[2].trim();
                    placeToyIfValid(x, y, face, keyword);
                }
                else {
                    if (initialPlaceCmd) {
                        System.out.println("Not a valid place command, it should have only 3 arguments like X,Y,FACE");
                        callPlace();
                    }
                }

            } else {
                if (initialPlaceCmd) {
                    System.out.println("Not a valid place command, valid syntax is in format PLACE X,Y,FACE (for eg: PLACE 0,0,NORTH) :");
                    callPlace();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Not a valid x/y cordinate in place command, it should be numbers between [0-5]");
            if (initialPlaceCmd) {
                callPlace();
            }
        }
    }

    private static void placeToyIfValid(int x, int y, String face, String keyword) {
        if (validateNumber(x) && validateNumber(y) && Arrays.asList(Direction.values()).contains(Direction.valueOf(face)) && Arrays.asList(InstructionType.values()).contains(InstructionType.valueOf(keyword))) {
            position = x + ToyUtils.COMMA + y + ToyUtils.COMMA + face;
            initialPlaceCmd = false;
            System.out.println("Toy has been placed");
        } else {
            System.out.println("Invalid placement, please verify command/x/y/face, place the toy again using proper PLACE command");
            if (initialPlaceCmd) {
                callPlace();
            }

        }

    }

    public static void main(String[] args) {
        callPlace();
        System.out.println("Enter sequence of commands to move toy over the table, valid commands are \n PLACE, MOVE, RIGHT, LEFT, REPORT ");
        ArrayList<String> seqofComnds = ToyUtils.readCommands();
        for (String command : seqofComnds) {
            String[] keywords = command.split(" ");
            String keyword = keywords[0];
            switch (keyword) {
                case ToyUtils.PLACE:
                    placeToy(command);
                    break;
                case ToyUtils.MOVE:
                    if(keywords.length==1){
                        moveToy();
                    }

                    break;
                case ToyUtils.LEFT:
                    if(keywords.length==1){
                        turnLeft();
                    }

                    break;
                case ToyUtils.RIGHT:
                    if(keywords.length==1){
                        turnRight();
                    }

                    break;
                case "REPORT":
                    reportDirection();
                    break;
                default:
                    System.out.println("Ignoring invalid command " + keyword);

            }
        }
    }
}

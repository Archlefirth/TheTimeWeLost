package model;

import java.util.ArrayList;

public class Dialogue {
    private static ArrayList<String>[] lineArr = new ArrayList[7];
    //set array of lines to 7 levels for now
    //We can use FileIO since this could get messy.
    private String player1;
    private String player2;

    /*
     * constructor for Dialogue class
     * @param p1 player 1's name
     * @param p2 player 2's name
     * 0 :Intro + Caveman
     * 1: Ancient Greece/Roman
     * 2: Middle Ages
     * 3: Age of Exploration/Sail
     * 4: WW1/WW2
     * 5: 70's/80's
     * 6: 2016
     */
    public Dialogue(String p1, String p2) {
        player1 = p1;
        player2 = p2;
        for (int i = 0; i < lineArr.length; i++) {
            lineArr[i] = new ArrayList<String>();
        }

        lineArr[0].add("Intro +  Caveman");
        lineArr[0].add(player1 + ": Yo let me touch this time machine!");
        lineArr[0].add(player2 + ": Paige...no more Papa John's ads");
        lineArr[0].add(player1 + ": AJ isn't funny");
        lineArr[0].add(player2 + ": Actually AJ is the best and could be a professional comedian if she wanted");
        lineArr[0].add(player1 + ": I'm sorry for what I said. Long live AJ!");

        lineArr[1].add("Ch.2 Ancient/Roman Greek Age");
        lineArr[1].add(player1 + ": Say something");
        lineArr[1].add(player2 + ": No!");


        lineArr[2].add("Ch.3 Middle Age");
        lineArr[2].add("Text");

        lineArr[3].add("Ch.4 Age of Sails");
        lineArr[3].add("Text");

        lineArr[4].add("Ch.5 WW1/WW2");
        lineArr[4].add("Text");

        lineArr[5].add("Ch.6 70's/80's");
        lineArr[5].add("Text");

        lineArr[6].add("Ch.7 2016");
        lineArr[6].add("Text");


    }

    public ArrayList<String>[] getDialogue(){
        return lineArr;
    }

}

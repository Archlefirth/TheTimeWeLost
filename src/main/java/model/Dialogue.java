package model;

import java.util.ArrayList;

public class Dialogue {
    private static ArrayList<String>[] lineArr = new ArrayList[8];
    //set array of lines to 7 levels for now
    //We can use FileIO since this could get messy.
    private String player1;
    private String player2;

    /*
     * constructor for Dialogue class
     * @param p1 player 1's name
     * @param p2 player 2's name
     * 0 : Introduction screen
     * 1: Cavemen
     * 2: Ancient Greece/Roman
     * 3: Middle Ages
     * 4: Age of Exploration/Sail
     * 5: WW1/WW2
     * 6: 70's/80's
     * 7: 2016
     */
    public Dialogue(String p1, String p2) {
        player1 = p1;
        player2 = p2;
        for (int i = 0; i < lineArr.length; i++) {
            lineArr[i] = new ArrayList<String>();
        }

        //intro narrative
        lineArr[0].add("This is an introduction page (narrative)");
        lineArr[0].add(player1 + ": Yo let me touch this time machine!");
        lineArr[0].add(player2 + ": Paige...no more Papa John's ads");
        lineArr[0].add(player1 + ": AJ isn't funny");
        lineArr[0].add(player2 + ": Actually AJ is the best and could be a professional comedian if she wanted");
        lineArr[0].add(player1 + ": I'm sorry for what I said. Long live AJ!");

        //Cavemen
        lineArr[1].add("Ch.1  Caveman Age");
        lineArr[1].add(player1 + ": Say something");
        lineArr[1].add(player2 + ": No!");


        //Ancient Greece/Roman
        lineArr[2].add("Ch.2 Ancient/Roman Greek Age");
        lineArr[2].add("Text");

        //Middle Ages
        lineArr[3].add("Ch.3 Middle Age");
        lineArr[3].add("Text");

        //Age of Exploration
        lineArr[4].add("Ch.4 Age of Sails");
        lineArr[4].add("Text");

        //WW1/WW2
        lineArr[5].add("Ch.5 WW1/WW2");
        lineArr[5].add("Text");

        //70's/80's
        lineArr[6].add("Ch.6 70's/80's");
        lineArr[6].add("Text");


        //2016
        lineArr[7].add("Ch.7 2016");
        lineArr[7].add("You win!");
    }

    public ArrayList<String>[] getDialogue(){
        return lineArr;
    }

}

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

        //intro narrative + Cavemen
        lineArr[0].add("Chapter 1: Cavemen");
        lineArr[0].add("Freya: How can you just lose the time machine codes? It’s a big, neon yellow book!");
        lineArr[0].add("Lanni: Well maybe we should make it glow-in-the-dark if you expect me to see in a time period \nwithout electricity!");
        lineArr[0].add("Freya: Candles! Lanterns! Where's the difficulty?");
        lineArr[0].add("Lanni: Let’s just get back to 2016. We have another copy at home.");
        lineArr[0].add("Freya: Fine. We can just start guessing until we make it back home.");
        lineArr[0].add("Lanni: Wait, but don’t you want to say bye to your family before we go?");
        lineArr[0].add("Freya: Har-dee-har-har. Let's go.");
        lineArr[0].add("After visiting countless points in history, Lanni Youkissas and Freya " +
                "find themselves \ntrapped in the wrong century without their book of time period codes." +
                " Complete the patterns \nto help them get back home!");

        //Ancient Greece/Roman
        lineArr[1].add("Chapter 2: Ancient Rome");
        lineArr[1].add("Lanni: You know, I've always wanted to visit Ancient Rome.");
        lineArr[1].add("Freya: Ever think you'd see it like this, in its prime?");
        lineArr[1].add("Lanni: Yes. I pictured traveling through space and time in a " +
                "rickety metal box to be right here \nin this very moment of history. Yeah.");
        lineArr[1].add("Freya: Okay, I can see you're getting cranky. Maybe you should" +
                " take a nap and let me try the \nnext time period.");
        lineArr[1].add("Lanni: Sure, I'd nap... if I wanted to end up back with the cavemen.");
        lineArr[1].add("Freya: No faith and so much sass. C'mon, let's go.");


        //Middle Ages
        lineArr[2].add("Chapter 3: Middle Ages");
        lineArr[2].add("Freya: No way. Do you think we can find King Arthur and the Round Table?");
        lineArr[2].add("Lanni: You know that's all a legend, right?");
        lineArr[2].add("Freya: Or maybe we just need to prove everyone wrong by finding him!");
        lineArr[2].add("Lanni: That's a terrible idea.");
        lineArr[2].add("Freya: ...you're just afraid of a little fun.");
        lineArr[2].add("Lanni: Fun is getting back home for the election or New Years. \nNot running around here searching for a king that doesn't exist.");
        lineArr[2].add("Freya: I bet you'll regret rushing home so fast.");

        //Age of Exploration
        lineArr[3].add("Chapter 4: Age of Exploration");
        lineArr[3].add("Freya: Are we on a ship?");
        lineArr[3].add("Lanni: I think we are. Is this the age of exploration?");
        lineArr[3].add("Freya: Must be, because I'm already sea sick. Can we hurry out of here?");
        lineArr[3].add("Lanni: But I kind of want to see where they end up.");
        lineArr[3].add("Freya: Oh so now you want to have fun? We're leaving.");

        //WW1/WW2
        lineArr[4].add("Chapter 5: World War II");
        lineArr[4].add("");

        //70's/80's
        lineArr[5].add("Chapter 6: 1980s");
        lineArr[5].add("Freya: At least we're getting close to 2016.");
        lineArr[5].add("Lanni: You know, the 80s was such a happen'en decade.");
        lineArr[5].add("Freya: Wha- oh my god, I think those are my parents!");
        lineArr[5].add("Lanni: No way! They're so hip!");
        lineArr[5].add("Freya: Nope. No. Don't ever say that again.");

        //2016
        //some sort of influx of memes and other 2016 junk
        lineArr[6].add("Final Chapter: 2016");
        lineArr[6].add("Lanni: Wha-what happened??");
        lineArr[6].add("Freya: How long have we been gone? What is this?");
        lineArr[5].add("Lanni: Dab? What is a dab?");
        lineArr[5].add("Freya: Do you hear that?");
        lineArr[5].add("Lanni: It sounds like... a really long 'ya boi?'");
        lineArr[5].add("Freya: ...wanna keep trying?");
        lineArr[5].add("Lanni: I'm already at the time machine.");
    }

    public ArrayList<String>[] getDialogue(){
        return lineArr;
    }

}

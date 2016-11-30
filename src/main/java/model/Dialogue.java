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
        // Player 1 = Lanni Youkissas' lines
        // Player 2 =  Freya's lines
        lineArr[0].add("50000 BC: You have reached the Age of Cavemen!");
        lineArr[0].add(player1 +  ": How can you just lose the time machine codes? It’s a big, neon yellow book!");
        lineArr[0].add(player2 +  ": Well maybe we should make it glow-in-the-dark if you expect me to see in a time period without electricity!");
        lineArr[0].add(player1 +  ": Candles! Lanterns! Electricity! Where's the difficulty?");
        lineArr[0].add(player2 +  ": Let’s just get back to 2016. We have another copy at home.");
        lineArr[0].add(player1 +  ": Fine. We can just start guessing until we make it back home.");
        lineArr[0].add(player2 +  ": Wait, but don’t you want to say bye to your family before we go?");
        lineArr[0].add(player1 +  ": Har-dee-har-har. Let's go.");
        lineArr[0].add("After travelling throughout history, " + player1 + " and " + player2 +
                " find themselves trapped in the wrong century without their book of time period codes." +
                " Complete the patterns to help them get back home!");

        //Ancient Greece/Roman
        lineArr[1].add("100 AD: Welcome to Ancient Rome!");
        lineArr[1].add(player1 + ": You know, I've always wanted to visit Ancient Rome.");
        lineArr[1].add(player2 + ": Ever think you'd see it like this, in its prime?");
        lineArr[1].add(player1 + ": Yes. I pictured traveling through space and time in a " +
                "rickety metal box to be right here in this very moment of history. Yeah.");
        lineArr[1].add(player2 + ": Okay, I can see you're getting cranky. Maybe you should" +
                " take a nap and let me try the next time period.");
        lineArr[1].add(player1 + ": Sure, I'd nap... if I wanted to end up back with the cavemen.");
        lineArr[1].add(player2 + ": No faith and so much sass. C'mon, let's go.");


        //Middle Ages
        lineArr[2].add("1080 AD: Your destination is on the right: The Middle Ages!");
        lineArr[2].add(player2 + ": No way. Do you think we can find King Arthur and the Round Table?");
        lineArr[2].add(player1 + ": You know that's all a legend, right?");
        lineArr[2].add(player2 + ": Or maybe we just need to prove everyone wrong by finding him!");
        lineArr[2].add(player1 + ": That's a terrible idea.");
        lineArr[2].add(player2 + ": ...you're just afraid of a little fun.");
        lineArr[2].add(player1 + ": Fun is getting back home for the election or New Years. Not running around here searching for a king that doesn't exist.");
        lineArr[2].add(player2 + ": I bet you'll regret rushing home so fast.");

        //Age of Exploration
        lineArr[3].add("1590 AD: You are in the Age of Exploration");
        lineArr[3].add(player2 + ": Are we on a ship?");
        lineArr[3].add(player1 + ": I think we are. Is this the age of sailing ships, famous explorers and gold doubloons?");
        lineArr[3].add(player2 + ": Must be, because I'm already sea sick. Can we get out of here?");
        lineArr[3].add(player1 + " But I kind of want to see where they end up.");
        lineArr[3].add(player2 + ": Oh so now you want to have fun? We're leaving.");

        //WW1/WW2
        lineArr[4].add("1943 AD: World War II");
        lineArr[4].add("");

        //70's/80's
        lineArr[5].add("1980 AD: I hope you have Disco Fever!");
        lineArr[5].add(player2 + ": At least we're getting close to 2016.");
        lineArr[5].add(player1 + ": You know, the 80s was such a happen'en decade.");
        lineArr[5].add(player2 + ": Wha- oh my god, I think those are my parents!");
        lineArr[5].add(player1 + ": No way! They're so hip!");
        lineArr[5].add(player2 + ": Nope. No. Don't ever say that again.");

        //2016
        //some sort of influx of memes and other 2016 junk
        lineArr[6].add("2016 AD: Hopefully you remembered your WiFi password");
        lineArr[6].add(player1 + ": Wha-what happened??");
        lineArr[6].add(player2 + ": How long have we been gone? What is this?");
        lineArr[5].add(player1 + ": Dab? What is a dab?");
        lineArr[5].add(player2 + ": Do you hear that?");
        lineArr[5].add(player1 + ": It sounds like... a really long 'ya boi?'");
        lineArr[5].add(player2 + ": ...wanna pick another year?");
        lineArr[5].add(player1 + ": I'm already at the time machine.");
    }

    public ArrayList<String>[] getDialogue(){
        return lineArr;
    }

}

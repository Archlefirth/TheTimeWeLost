package model;

public class PatternChar {
    private char character;
    private String player;
    public PatternChar(char character, String player) {
        this.character = character;
        this.player = player;
    }

    public char getCharacter(){
        return character;
    }

    public String getPlayer() {
        return player;
    }
}

package net.andrasia.teambuilder;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String language;
    private Game game;
    private ArrayList<String> gamertags;

    public User() {}

    public User(String language, String gamertag, Game game) {
        this.language = language;
        this.game = game;
        fillArrayListBasedOfGame(gamertag);
    }

    public void fillArrayListBasedOfGame(String gamertag) {
        gamertags = new ArrayList<>();
        gamertags.add(gamertag);
        for(int i = 1; i < game.getRequiredPlayerCount(); i++){
            gamertags.add("");
        }
    }

    public void addGamertagToArray(String gamertag){
        for (int i = 0; i < gamertags.size(); i++){
            if(gamertags.get(i).equals("")){
                gamertags.add(i, gamertag);
                break;
            }
        }
    }

    public String pullOriginalGamertag(){
        return gamertags.get(0);
    }

    public ArrayList<String> getGamertags(){
        return gamertags;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isCompatible(User other){
        return other.game.equals(game) && other.language.equals(language);
    }
}
package net.andrasia.teambuilder;

import android.util.Log;

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
            gamertags.add("0");
        }
    }

    public void addGamertagToArray(String gamertag){
        for (int i = 0; i < gamertags.size(); i++){
            if(gamertags.get(i).equals("0")){
                gamertags.add(i, gamertag);
                gamertags.remove(gamertags.size()-1);
                break;
            }
        }
    }

    public void removeGamerTagFromArray(String gamertag){
        for(int i = 0; i < gamertags.size(); i++){
            if(gamertags.get(i).equals(gamertag)){
                gamertags.remove(i);
                gamertags.add("0");
            } else if(gamertags.get(0).equals("0")) {
                gamertags.clear();
            }
        }
    }

    public boolean hasOnlyOneEntry(){
        int j = 0;
        for(int i = 0; i < gamertags.size(); i++){
            if(!gamertags.get(i).equals("0")){
                j++;
            }
        }
        return j == 1;
    }

    public void logGamertags(){
        for (int i = 0; i < gamertags.size(); i++){
            Log.d("BOI", gamertags.get(i));
        }
    }

    public boolean checkForGamertag(String gamertag){
        for(int i = 0; i < gamertags.size(); i++){
            if(gamertags.get(i).equals(gamertag)){
                return true;
            } else {
                return false;
            }
        }
        return false;
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
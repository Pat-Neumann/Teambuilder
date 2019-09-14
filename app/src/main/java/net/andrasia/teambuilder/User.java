package net.andrasia.teambuilder;

import java.io.Serializable;

public class User implements Serializable {
    private String language, gamertag;
    private Game game;

    public User() {}

    public User(String language, String gamertag, Game game) {
        this.language = language;
        this.gamertag = gamertag;
        this.game = game;
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

    public String getGamertag() {
        return gamertag;
    }

    public void setGamertag(String gamertag) {
        this.gamertag = gamertag;
    }

    public boolean isCompatible(User other){
        return other.game.equals(game) && other.language.equals(language);
    }
}
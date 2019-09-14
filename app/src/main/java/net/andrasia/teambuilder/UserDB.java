package net.andrasia.teambuilder;

import android.util.Log;

public class UserDB {
    private String game, language, gamertag;

    public UserDB(String game, String language, String gamertag) {
        this.game = game;
        this.language = language;
        this.gamertag = gamertag;
    }

    public String getDBgame() {
        return game;
    }

    public String getDBLanguage() {
        return language;
    }

    public String getDBGamertag() {
        return gamertag;
    }

    public void createLogs(){
        Log.d("USERDB", game);
        Log.d("USERDB", language);
        Log.d("USERDB", String.valueOf(gamertag));
    }
}
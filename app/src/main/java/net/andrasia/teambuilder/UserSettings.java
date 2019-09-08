package net.andrasia.teambuilder;


public class UserSettings {
    private String gamerTag;
    private String languages;
    private String games;



    public UserSettings() {
    }

    public UserSettings(String gamerTag, String languages, String games) {
        this.gamerTag = gamerTag;
        this.languages = languages;
        this.games = games;


    }

    public String getGamerTag() {
        return gamerTag;
    }

    public void setGamerTag(String gamerTag) {
        this.gamerTag = gamerTag;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getGames() {
        return games;
    }

    public void setGames(String games) {
        this.games = games;
    }


}

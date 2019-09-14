package net.andrasia.teambuilder;

public enum Game {
    LEAGUE_OF_LEGENDS,
    APEX_LEGENDS,
    COUNTER_STRIKE_GLOBAL_OFFENSIVE,
    OVERWATCH,
    UNDEFINED;

    private int requiredPlayerCount;
    private String gameName;
    static {
        LEAGUE_OF_LEGENDS.requiredPlayerCount = 5;
        LEAGUE_OF_LEGENDS.gameName = "League of Legends";

        APEX_LEGENDS.requiredPlayerCount = 3;
        APEX_LEGENDS.gameName = "Apex Legends";

        COUNTER_STRIKE_GLOBAL_OFFENSIVE.requiredPlayerCount = 5;
        COUNTER_STRIKE_GLOBAL_OFFENSIVE.gameName = "CS:GO";

        OVERWATCH.requiredPlayerCount = 6;
        OVERWATCH.gameName = "Overwatch";

        UNDEFINED.requiredPlayerCount = 0;
        UNDEFINED.gameName = "Undefined";
    }

    public int getRequiredPlayerCount() {
        return requiredPlayerCount;
    }

    public String getGameName() {
        return gameName;
    }

    public static Game fromGameName(String name){
        for(Game game : Game.values()){
            if(game.gameName.equals(name)){
                return game;
            }
        }
        return Game.UNDEFINED;
    }
}

package com.jkbff.saca;

public class Player {
    private String name;
    private String characterId;
    private String server;
    private String faction;

    public Player(String name, String characterId, String server, String faction) {
        this.name = name;
        this.characterId = characterId;
        this.server = server;
        this.faction = faction;
    }

    public String getName() {
        return name;
    }

    public String getCharacterId() {
        return characterId;
    }

    public String getServer() {
        return server;
    }

    public String getFaction() {
        return faction;
    }

    public String toString() {
        return "Player(" + name + ", " + characterId + ", " + server + ", " + faction + ")";
    }
}

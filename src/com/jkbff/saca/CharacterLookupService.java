package com.jkbff.saca;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

class CharacterLookupService {
    private HttpClient client = HttpClientBuilder.create().build();

    public CharacterLookupService() {

    }

    public Player lookupCharacter(String characterName) {
        HttpGet request = new HttpGet("https://census.daybreakgames.com/get/ps2/character?c:resolve=world&name.first_lower=" + characterName.toLowerCase());
        try {
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);

            JSONObject json = new JSONObject(result);
            String name = json.getJSONArray("character_list").getJSONObject(0).getJSONObject("name").getString("first");
            String characterId = json.getJSONArray("character_list").getJSONObject(0).getString("character_id");
            String factionId = json.getJSONArray("character_list").getJSONObject(0).getString("faction_id");
            String worldId = json.getJSONArray("character_list").getJSONObject(0).getString("world_id");

            Player player = new Player(name, characterId, getWorld(worldId), getFaction(factionId));
            return player;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getWorld(String worldId) {
        // taken from: http://census.daybreakgames.com/get/ps2:v2/world?c:limit=100
        switch (worldId) {
            case "1":
                return "Connery";
            case "17":
                return "Emerald";
            default:
                throw new RuntimeException("Unknown world id: '" + worldId + "'");
        }
    }

    private String getFaction(String factionId) {
        // taken from: http://census.daybreakgames.com/get/ps2:v2/faction?c:limit=100
        switch (factionId) {
            case "1":
                return "VS";
            case "2":
                return "NC";
            case "3":
                return "TR";
            case "4":
                return "NS";
            default:
                throw new RuntimeException("Unknown faction id: '" + factionId + "'");
        }
    }
}

package fr.zaral.beeconnected.database;


import org.json.JSONObject;

/**
 * @author Zaral
 */
public class HiveData {

    private int lum, weight, humidity, temp, id;
    private long timestamp;

    public HiveData(int lum, int weight, int humidity, int temp, int id, long timestamp) {
        this.lum = lum;
        this.weight = weight;
        this.humidity = humidity;
        this.temp = temp;
        this.id = id;
        this.timestamp = timestamp;
    }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("lum", lum)
                .put("weight", weight)
                .put("humidity", humidity)
                .put("temp", temp)
                .put("timestamp", timestamp)
                .put("id", id);
        return obj;
    }

}

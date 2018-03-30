package fr.zaral.beeconnected.database;


import org.json.JSONObject;

/**
 * @author Zaral
 */

//HiveData est un objet regroupant les données envoyé en même temps
//Toutes les données stocké dans cet objet ont donc été envoyé en même temps

public class HiveData {

    //Données
    private int lum, weight, humidity, temp, /*Id de la ruche */id;

    //Date de récupération des données (depuis la ruche)
    private long timestamp;

    //Constructeur: ici sont définies les données
    public HiveData(int lum, int weight, int humidity, int temp, int id, long timestamp) {
        this.lum = lum;
        this.weight = weight;
        this.humidity = humidity;
        this.temp = temp;
        this.id = id;
        this.timestamp = timestamp;
    }

    //Convertie l'objet en Object JSON permettant ainsi de convertir cet objet en universel
    //Les données pourrons facilement être luent sur d'autres platformes.
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

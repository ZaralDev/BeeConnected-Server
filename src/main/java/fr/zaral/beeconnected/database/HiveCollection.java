package fr.zaral.beeconnected.database;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zaral
 */
public class HiveCollection {

    private MongoCollection<Document> collection;

    public HiveCollection(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    public void registerAccount(int uuid, int lum, int temp, int humidity, int weight ,long timestamp) {
        Document doc = new Document("uuid", uuid)
                .append("lum", lum)
                .append("temp", temp)
                .append("humidity", humidity)
                .append("weight", weight)
                .append("timestamp", timestamp);
        collection.insertOne(doc);
    }

    public List<HiveData> getByID(int id, int limit) {
        List<HiveData> data = new ArrayList<>();
        FindIterable<Document> docs = collection.find(new Document("uuid", id)).sort(new Document("timestamp", -1)).limit(limit);
        MongoCursor<Document> it = docs.iterator();
        while(it.hasNext()) {
            Document doc = it.next();
            int lum = doc.getInteger("lum");
            int weight = doc.getInteger("weight");
            int humidity = doc.getInteger("humidity");
            long timestamp = doc.getLong("timestamp");
            int temp = doc.getInteger("temp");
            data.add(new HiveData(lum, weight, humidity,temp, id, timestamp));
        }
        return data;
    }

}

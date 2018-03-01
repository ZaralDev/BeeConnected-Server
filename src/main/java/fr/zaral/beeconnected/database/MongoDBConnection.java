package fr.zaral.beeconnected.database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.pmw.tinylog.Logger;

/**
 * @author Zaral
 */
public class MongoDBConnection {

    private final String HIVE_DATABASE = "BeeConnected";
    private final String HIVE_COLLECTION_NAME = "hive";

    private MongoClient mongo;
    private HiveCollection hiveCollection;


    private int port;
    private String ip;

    public MongoDBConnection(int port, String ip) {
        Logger.info("Loading database connection...");
        this.port = port;
        this.ip = ip;
    }


    public void connect() {
        mongo = new MongoClient(ip, port);
        MongoDatabase database = mongo.getDatabase(HIVE_DATABASE);
        hiveCollection = new HiveCollection(database.getCollection(HIVE_COLLECTION_NAME));
    }


    public HiveCollection getHiveCollection() {
        return hiveCollection;
    }
}

package fr.zaral.beeconnected.database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.pmw.tinylog.Logger;

/**
 * @author Zaral
 */

//Classe mère pour la gestion de la base de donnée
//Ici les tables sont crées
public class MongoDBConnection {

    //La base de donnée sur laquelle on travail
    private final String HIVE_DATABASE = "BeeConnected";
    //La collection (ou table)
    private final String HIVE_COLLECTION_NAME = "hive";

    //L'objet nous permettant de nous connecter sur la base de donnée
    private MongoClient mongo;

    //L'objet gerant la collection
    private HiveCollection hiveCollection;

    //Port de la base de donnée
    private int port;
    //Ip de la base de donnée
    private String ip;

    //Constructeur
    public MongoDBConnection(int port, String ip) {
        //Message dans la console
        Logger.info("Loading database connection...");
        this.port = port;
        this.ip = ip;
    }

    //Fonction initialisant la connection
    public void connect() {
        //On créé le client
        mongo = new MongoClient(ip, port);
        //On récupère la base de donnée
        MongoDatabase database = mongo.getDatabase(HIVE_DATABASE);
        //On créé notre collection
        hiveCollection = new HiveCollection(database.getCollection(HIVE_COLLECTION_NAME));
    }


    //Fonction permettant de récupérer notre collection
    public HiveCollection getHiveCollection() {
        return hiveCollection;
    }
}

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

    //Il s'agit de notre base de donnée
    private MongoCollection<Document> collection;

    //Constructeur
    public HiveCollection(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    //Permet d'insérer dans la base de donnée les données reçus
    public void insertData(int uuid, int lum, int temp, int humidity, int weight ,long timestamp) {
        //On créé un objet de type Document ou l'on ajoute toutes nos valeurs
        Document doc = new Document("uuid", uuid)
                .append("lum", lum)
                .append("temp", temp)
                .append("humidity", humidity)
                .append("weight", weight)
                .append("timestamp", timestamp);

        //On l'ajoute dans la base de donnée
        collection.insertOne(doc);
    }

    //Fonction permettant de récupérer les informations dans la base de donnée
    //L'id représente l'id de la ruche, et la limite est le nombre de données que nous allons renvoyer
    //Les données renvoyés seront par ordre décroissant du timestamp
    //Le timestamp représente le nombre de secondes écoulées depuis le 1er janvier 1970, la valeur timestamp est universelle et sera
    //toujours configuré de la même facon (secondes depuis 1er janvier 1970)
    public List<HiveData> getByID(int id, int limit) {

        //On créé une liste d'objets de type HiveData (+ d'info dans la classe en question)
        List<HiveData> data = new ArrayList<>();

        //On fait une requete sur la base de donnée en récupérant toutes les données venant de l'id donné en paramètre.
        //On les tries ensuite par ordre décroissant et on en garde X
        //Si la variable 'limit' est définie à 0, on renvoie toutes les données, il n'y a donce pas de limite
        FindIterable<Document> docs = collection.find(new Document("uuid", id)).sort(new Document("timestamp", -1)).limit(limit);

        //On converti l'objet en liste
        MongoCursor<Document> it = docs.iterator();

        //Une boucle qui s'éxécute tant que la liste n'a pas été parcourue entierement
        while(it.hasNext()) {
            //On récupère l'objet
            Document doc = it.next();

            //On récupère les données de cet objet
            int lum = doc.getInteger("lum");
            int weight = doc.getInteger("weight");
            int humidity = doc.getInteger("humidity");
            long timestamp = doc.getLong("timestamp");
            int temp = doc.getInteger("temp");

            //On créé un objet 'HiveData' que l'on ajoute dans notre liste créé au début de la fonction
            data.add(new HiveData(lum, weight, humidity,temp, id, timestamp));
        }

        //On renvoie la liste créée qui est maintenant remplie
        return data;
    }

}

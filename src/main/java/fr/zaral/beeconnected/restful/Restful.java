package fr.zaral.beeconnected.restful;

import fr.zaral.beeconnected.database.MongoDBConnection;
import fr.zaral.beeconnected.restful.routes.Data;
import fr.zaral.beeconnected.restful.routes.PostData;
import fr.zaral.beeconnected.restful.routes.Test;
import org.pmw.tinylog.Logger;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;


import static spark.Spark.*;

/**
 * @author Zaral
 */

//Une API  RESTful , est une interface de programmation d'application qui fait appel à des requêtes HTTP
// pour obtenir (GET), placer (PUT), publier (POST) et supprimer (DELETE) des données.
//Pour le projet, on s'en sert pour renvoyer les informations au client ayant fait une requête HTTP sur un url, ou pour
//Stocker les données.
//Pour gérer cela, j'ai utilisé l'api SPARK qui permet en Java de créé un serveur web et de le gérer sans trop de
//difficultés. Cependant, je dois héberger le programme sur mon serveur personnel et le faire tourner, ayant déjà un autre
//serveur web, j'ai du utiliser un port différent que celui par défaut (80) pour gérer les requêtes
public class Restful {


    //Port d'écoute
    private int port;

    //Objet permettant de récupérer les données
    private MongoDBConnection mongoDBConnection;


    //Constructeur
    public Restful(int port, MongoDBConnection mongo) {
        this.port = port;
        this.mongoDBConnection = mongo;
    }

    //Objet permettant de récupérer les données
    public MongoDBConnection getMongoDBConnection() {
        return mongoDBConnection;
    }

    private static void enableCORS(final String origin, final String methods, final String headers) {
        before(new Filter() {
            @Override
            public void handle(Request request, Response response) {
                response.header("Access-Control-Allow-Origin", origin);
                response.header("Access-Control-Request-Method", methods);
                response.header("Access-Control-Allow-Headers", headers);
            }
        });
    }


    public void listen() {
        Logger.info("Loading restful api on port " + port);

        //On définie le port
        port(port);

        enableCORS("*", "*", "*");

        //On créé une url qui va être '/api/data' de type get, Ici on va récupérer les données du serveur, C'est l'objet Data qui va s'occupé du traitement
        //de la requête
        Spark.get("/api/data", new Data(this));
        //On créé une url qui va être '/api/postdata' de type post, Ici on va dire quelles information nous devons stocké sur le serveur, c'est l'objet PostData qui
        //va s'occupé du traitement de la requête
        Spark.post("/api/postdata", new PostData(this));
        //Une url de test pour s'assurer que le serveur fonctionne correctement
        Spark.post("/api/test", new Test(this));

    }

}


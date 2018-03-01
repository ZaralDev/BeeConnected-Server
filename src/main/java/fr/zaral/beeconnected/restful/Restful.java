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
public class Restful {


    private int port;


    private MongoDBConnection mongoDBConnection;



    public Restful(int port, MongoDBConnection mongo) {
        this.port = port;
        this.mongoDBConnection = mongo;
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

    public MongoDBConnection getMongoDBConnection() {
        return mongoDBConnection;
    }

    public void listen() {
        Logger.info("Loading restful api on port " + port);

        port(port);
        enableCORS("*", "*", "*");
        Spark.get("/api/data", new Data(this));
        Spark.post("/api/postdata", new PostData(this));
        Spark.post("/api/test", new Test(this));

    }

}


package fr.zaral.beeconnected.restful;

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



    public Restful(int port) {
        this.port = port;
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

        port(port);
        enableCORS("*", "*", "*");
        Spark.post("/api/test", new Test(this));
    }

}


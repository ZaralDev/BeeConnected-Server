package fr.zaral.beeconnected.restful.routes;

import fr.zaral.beeconnected.restful.Restful;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;

public class Data extends IRoute {

    public Data(Restful restful) {
        super(restful, "Data");
    }

    @Override
    protected Object work(Request request, Response response) throws Exception {

        QueryParamsMap light = request.queryMap("light");

        return null;
    }
}

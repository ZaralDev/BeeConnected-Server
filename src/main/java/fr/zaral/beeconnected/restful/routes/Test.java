package fr.zaral.beeconnected.restful.routes;

import fr.zaral.beeconnected.restful.Restful;
import org.pmw.tinylog.Logger;
import spark.Request;
import spark.Response;

public class Test  extends IRoute{


    public Test(Restful restful) {
        super(restful, "Test");
    }

    //Requête test
    @Override
    protected Object work(Request request, Response response) throws Exception {
        for (String param : request.queryParams()) {
            Logger.info("Param: " +  param + " Value: " + request.queryParams(param));
        }
        return "Ok";
    }
}

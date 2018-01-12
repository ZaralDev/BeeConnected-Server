package fr.zaral.beeconnected.restful.routes;

import fr.zaral.beeconnected.restful.Restful;
import org.pmw.tinylog.Logger;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * @author Zaral
 */
public abstract class IRoute implements Route {

    protected Restful restful;
    private String requestName;

    public IRoute(Restful restful, String name) {
        this.restful = restful;
        this.requestName = name;
    }

    public String getName() {
        return requestName;
    }

    protected abstract Object work(Request request, Response response) throws Exception;


    @Override
    public Object handle(Request request, Response response) {
        Logger.info("[" + requestName + "] from " + request.ip());
        Object object = null;
        try {
            object = work(request, response);
        } catch (Exception e) {
            Logger.info(e);
        }

        Logger.info("[" + requestName + "] from " + request.ip() + " result: " + object);

        return object;
    }


}

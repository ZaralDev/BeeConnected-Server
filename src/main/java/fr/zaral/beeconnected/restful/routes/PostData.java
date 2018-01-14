package fr.zaral.beeconnected.restful.routes;

import fr.zaral.beeconnected.restful.Restful;
import org.json.JSONObject;
import spark.Request;
import spark.Response;


/**
 * @author Zaral
 */
public class PostData  extends IRoute {

    public PostData(Restful restful) {
        super(restful, "Data");
    }

    @Override
    protected Object work(Request request, Response response) throws Exception {

        String id = request.queryParams("id");
        String weight = request.queryParams("weight");
        String lum = request.queryParams("lum");
        String temp = request.queryParams("temp");
        String humidity = request.queryParams("humidity");
        String timestamp = request.queryParams("timestamp");

        JSONObject result = new JSONObject();
        if (id == null || weight == null || lum == null || temp == null  || humidity == null  || timestamp == null ) {
            result.put("success", false).put("reason", "Les parametres ne sont pas définis");
            return result;
        }
        try {
            int iId = Integer.parseInt(id);
            int iweight = Integer.parseInt(weight);
            int iLum = Integer.parseInt(lum);
            int itemp = Integer.parseInt(temp);
            int iHum = Integer.parseInt(humidity);
            long lTimestamp = Long.parseLong(timestamp);
            restful.getMongoDBConnection().getHiveCollection().registerAccount(iId, iLum, itemp, iHum, iweight, lTimestamp);
        } catch(NumberFormatException e) {
            result.put("success", false).put("reason", "Les parametres ne sont pas correct");
            return result;
        }
        result.put("success", true);
        return result;
    }
}
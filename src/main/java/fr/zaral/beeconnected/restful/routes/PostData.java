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

        //On récupère les données envoyés par le raspberry
        String id = request.queryParams("id");
        String weight = request.queryParams("weight");
        String lum = request.queryParams("lum");
        String temp = request.queryParams("temp");
        String humidity = request.queryParams("humidity");
        String timestamp = request.queryParams("timestamp");

        JSONObject result = new JSONObject();
        //On vérifie qu'elles soient correctent
        if (id == null || weight == null || lum == null || temp == null  || humidity == null  || timestamp == null ) {
            result.put("success", false).put("reason", "Les parametres ne sont pas définis");
            return result;
        }
        try {
            //On convertie les données en Integer
            int iId = Integer.parseInt(id);
            int iweight = Integer.parseInt(weight);
            int iLum = Integer.parseInt(lum);
            int itemp = Integer.parseInt(temp);
            int iHum = Integer.parseInt(humidity);
            long lTimestamp = Long.parseLong(timestamp);
            //On écrit dans la base de donnée les données récus
            restful.getMongoDBConnection().getHiveCollection().insertData(iId, iLum, itemp, iHum, iweight, lTimestamp);
        } catch(NumberFormatException e) {
            //Si une erreur on le dit au client
            result.put("success", false).put("reason", "Les parametres ne sont pas correct");
            return result;
        }
        //Sinon on renvoie que la requête s'est bien effectuée
        result.put("success", true);
        return result;
    }
}
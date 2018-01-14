package fr.zaral.beeconnected.restful.routes;

import fr.zaral.beeconnected.database.HiveData;
import fr.zaral.beeconnected.restful.Restful;
import org.json.JSONArray;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.util.List;

public class Data extends IRoute {

    public Data(Restful restful) {
        super(restful, "Data");
    }

    @Override
    protected Object work(Request request, Response response) throws Exception {

        String id = request.queryParams("id");
        String limit = request.queryParams("limit");
        JSONObject result = new JSONObject();

        if (id == null || limit == null) {
            result.put("success", false).put("reason", "Les parametres ne sont pas définis");
            return result;
        }

        JSONArray array = new JSONArray();
        List<HiveData> data = restful.getMongoDBConnection().getHiveCollection().getByID(Integer.parseInt(id), Integer.parseInt(limit));
        data.forEach(d-> array.put(d.toJson()));
        result.put("data", array);
        result.put("success", true);
        return result;
    }
}

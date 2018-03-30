package fr.zaral.beeconnected.restful.routes;

import fr.zaral.beeconnected.database.HiveData;
import fr.zaral.beeconnected.restful.Restful;
import org.json.JSONArray;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.util.List;

//Comme nous utilisons l'api SPARK, pour gérer les requêtes, notre classe doit hérité de la classe abstraite IRoute que j'ai créé qui hérite elle même
//D'une classe de SPARK
//Cela va nous 'obliger' à créer une fonction nommée work
public class Data extends IRoute {

    public Data(Restful restful) {
        super(restful, "Data");
    }

    @Override
    protected Object work(Request request, Response response) throws Exception {
        //On récupère l'id de la ruche voulu
        String id = request.queryParams("id");
        //Le nombre de données que l'on veut récupéré
        String limit = request.queryParams("limit");
        //On prépare notre objet JSON qui va nous permettre de renvoyer une réponse
        JSONObject result = new JSONObject();

        //On vérifie si la requête est correcte et donc éxecutable
        if (id == null || limit == null) {
            //Si elle n'est pas correcte on affiche un message d'erreur en réponse
            result.put("success", false).put("reason", "Les parametres ne sont pas définis");
            return result;
        }

        //On créé une list JSON qui nous permet de mettre plusieurs Objets json
        JSONArray array = new JSONArray();
        //On éxécute la recherche pour récupérer nos données sous une liste
        List<HiveData> data = restful.getMongoDBConnection().getHiveCollection().getByID(Integer.parseInt(id), Integer.parseInt(limit));
        //On convertie notre liste en JSON et on l'ajoute dans notre list qui gère le json
        data.forEach(d-> array.put(d.toJson()));
        //On ajoute la liste de JSON a notre json principal
        result.put("data", array);
        //On indique que la requête s'est déroulée correctement
        result.put("success", true);
        //On renvoie l'objet JSON en  tant que érponse
        return result;
    }
}

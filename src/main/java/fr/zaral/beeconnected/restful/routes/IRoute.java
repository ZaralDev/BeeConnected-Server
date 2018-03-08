package fr.zaral.beeconnected.restful.routes;

import fr.zaral.beeconnected.restful.Restful;
import org.pmw.tinylog.Logger;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * @author Zaral
 */

//IRoute est une classe abstraite (qui ne peut donc pas être initialisée directement) implémentant de l'interface Route venant de l'api SPARK
public abstract class IRoute implements Route {

    //L'attribue protected permet aux classe héritant de IRoute d'utiliser cette variable
    protected Restful restful;
    //Le nom de la requête, c'est utile pour le débug
    private String requestName;

    //Constructeur
    public IRoute(Restful restful, String name) {
        this.restful = restful;
        this.requestName = name;
    }

    //Fonction non utilisée, on s'en sert si il y a des problemes et que l'on doit trouvé d'ou vient l'erreur
    public String getName() {
        return requestName;
    }

    //Fonction que la classe héritant de IROUTE doit implémenter OBLIGATOIREMENT (sauf si c'est une classe abstraite)
    protected abstract Object work(Request request, Response response) throws Exception;

    //Fonction héritant de l'interface Route
    @Override
    public Object handle(Request request, Response response) {
        //On écrit dans la console la provenance de la requête
        Logger.info("[" + requestName + "] from " + request.ip());
        Object object = null;
        try {
            //On demande a la classe qui hérite de IRoute de traiter la requête et on récupere le résultat
            object = work(request, response);
        } catch (Exception e) {
            //Si une erreur s'est produite, on l'affiche dans la console
            Logger.info(e);
        }
        //On affiche le résultat dans la console
        Logger.info("[" + requestName + "] from " + request.ip() + " result: " + object);
        //On renvoie au client le résultat
        return object;
    }


}

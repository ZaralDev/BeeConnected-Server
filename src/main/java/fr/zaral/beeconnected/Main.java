package fr.zaral.beeconnected;

import fr.zaral.beeconnected.database.MongoDBConnection;
import fr.zaral.beeconnected.restful.Restful;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;
import org.pmw.tinylog.writers.ConsoleWriter;
import org.pmw.tinylog.writers.FileWriter;


/**
 * @author Zaral
 */
public class Main {

    //Fonction appellée lors du démarrage du programme
    public static void main(String[] args) {

        //Formate les message dans la console pour qu'il y est la date d'afficher et que tout soit loggé
        //dans un fichier. Cela permet en cas de crash de savoir ce qu'il s'est passé
        Configurator.defaultConfig()
                .formatPattern("{date:dd/MM/yyyy HH:mm:ss} [{level}] {message}")
                .writer(new ConsoleWriter())
                .addWriter(new FileWriter("log.txt"))
                .level(Level.TRACE) //Active le debug
                .activate();

        //Initialisation de la base de donnée MongoDB en local sur le port de la base de donnée (27017)
       MongoDBConnection db = new MongoDBConnection(27017, "127.0.0.1");
       db.connect();


       //Initialisation de l'API restful sur le port 3925 (+ d'info dans la classe Restful)
        Restful rest = new Restful(3925, db);
        rest.listen();
        Logger.info("Démarrage du Serveur de BeeConnected");

        //Le serveur est pret !


    }
}

package fr.zaral.beeconnected;

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

    public static void main(String[] args) {
        Configurator.defaultConfig()
                .formatPattern("{date:dd/MM/yyyy HH:mm:ss} [{level}] {message}")
                .writer(new ConsoleWriter())
                .addWriter(new FileWriter("log.txt"))
                .level(Level.DEBUG) //Active le debug
                .activate();
        Restful rest = new Restful(80);
        rest.listen();
        Logger.info("Démarrage du Serveur de BeeConnected");

    }
}

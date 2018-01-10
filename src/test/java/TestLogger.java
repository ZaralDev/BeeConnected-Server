import junit.framework.TestCase;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;
import org.pmw.tinylog.writers.ConsoleWriter;
import org.pmw.tinylog.writers.FileWriter;

/**
 * @author Zaral
 */
public class TestLogger extends TestCase {

    public TestLogger() {
        Configurator.defaultConfig()
                .formatPattern("{date:dd/MM/yyyy HH:mm:ss} [{level}] {message}")
                .writer(new ConsoleWriter())
                .addWriter(new FileWriter("log.txt"))
                .level(Level.DEBUG)
                .activate();

    }

    public void testLogger() {
        Logger.info("Test Log Info");
        Logger.debug("Test Log Debug");
        Logger.error("Test Log Error");
        Logger.warn("Test Log Warn");

    }
}

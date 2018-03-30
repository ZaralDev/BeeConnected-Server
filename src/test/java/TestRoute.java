import fr.zaral.beeconnected.restful.Restful;
import fr.zaral.beeconnected.utils.HttpUtils;
import junit.framework.TestCase;

import java.io.IOException;

public class TestRoute extends TestCase {

    public void testRoute() {
        Restful rest = new Restful(27018, null);
        rest.listen();

        try {
            String result = HttpUtils.sendPost("http://localhost:27018/api/test", "");
            assertNotNull(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

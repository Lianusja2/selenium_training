package Task10.app.test;
import org.junit.Before;
import Task10.app.app.Application;
/**
 * Created by inna.pshenychna on 7/2/2017.
 */
public class TestBase {

        public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
        public Application app;

        @Before
        public void start() {
            if (tlApp.get() != null) {
                app = tlApp.get();
                return;
            }


            app = new Application();
            tlApp.set(app);

            Runtime.getRuntime().addShutdownHook(
                    new Thread(() -> { app.quit(); app = null; }));
        }

}

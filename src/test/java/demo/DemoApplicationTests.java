package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.test.asciidoc.DemoApplication;
import org.test.asciidoc.HelloController;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
public class DemoApplicationTests {

    @Autowired
    HelloController helloController;

	@Test
	public void contextLoads() {
        String home = helloController.toString();
        System.out.println("home = " + home);
        assertNotNull(home);
    }

}

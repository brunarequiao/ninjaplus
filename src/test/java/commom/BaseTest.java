package commom;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import pages.MoviePage;
import pages.Sidebar;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.screenshot;

public class BaseTest {
    protected static LoginPage Login;
    protected static Sidebar side;
    protected static MoviePage movie;

    @BeforeMethod
    public void start() {

        Properties prop = new Properties();
        InputStream inputFile = getClass().getClassLoader().getResourceAsStream("config.properties");

        try {
            prop.load(inputFile);
        } catch (Exception ex) {
            System.out.println("Não foi possível carregar o config.properties. Trace =>" + ex.getMessage());
        }

        Configuration.browser = prop.getProperty("browser");
        Configuration.baseUrl = prop.getProperty("url");
        Configuration.timeout = Long.parseLong(prop.getProperty("timeout"));

        Login = new LoginPage();
        side = new Sidebar();
        movie = new MoviePage();
    }

    @AfterMethod
    public void finish() {
        //tira screenshot pelo selenide
        String tempShot = screenshot("temp_shot1");

        //transforma em binário para anexar no report do allure
        try {
           BufferedImage bimage = ImageIO.read(new File(tempShot));
           ByteArrayOutputStream baos = new ByteArrayOutputStream();

           ImageIO.write(bimage, "png", baos);

           byte [] finalshot = baos.toByteArray();

            io.qameta.allure.Allure.addAttachment("Evidência", new ByteArrayInputStream(finalshot));

        } catch (Exception ex) {
            System.out.println("Deu erro ao anexar. Trace => " + ex.getMessage());
        }
    }
}

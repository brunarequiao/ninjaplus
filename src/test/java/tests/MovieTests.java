package tests;

import commom.BaseTest;
import libs.Database;
import models.MovieModel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Arrays;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;


public class MovieTests extends BaseTest {

    private Database db;

    @BeforeMethod
    public void setUp() {
        Login
                .open()
                .with("bruna@teste.com", "pwd123");
        side.loggedUser().shouldHave(text("Bruna"));
    }

    @BeforeSuite
    public void delorean() {
        db = new Database();
        db.resetMovies();
    }

    @Test
    public void shouldRegisterANewMovie() {


        MovieModel movieData = new MovieModel(
                "Jumanji - Próxima fase",
                2020,
                "16/01/2020",
                Arrays.asList("The Rock", "Jack Black", "Kevin Hart", "Karen Gillan", "Danny DeVito"),
                "Tentando revisitar o mundo de Jumanji, Spencer decide consertar o bug no jogo que permite que" +
                "sejam transportados ao local",
                "Jumanji-720x1000.jpg"
        );

        movie
                .add()
                .create(movieData)
                .items().findBy(text(movieData.title)).shouldBe(visible);
    }

    @Test
    public void shouldSearchMovie() {
        movie.search("Batman").items().shouldHaveSize(2);
    }

    @AfterMethod
    public void cleanUp() {
        Login.clearSession();
    }
}

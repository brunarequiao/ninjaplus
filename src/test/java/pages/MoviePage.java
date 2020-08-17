package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import models.MovieModel;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.io.File;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MoviePage {

    public MoviePage add() {
        $(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/div/div[1]/div/div[1]/h4/button")).click();
        return this;
    }

    public MoviePage search(String value) {
        $(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/div/div[1]/div/div[2]/div/form/div/input")).setValue(value);
        $(By.xpath("//*[@id=\"search-movie\"]")).click();
        return this;
    }

    public MoviePage create(MovieModel movie) {
        $(By.xpath("//*[@id=\"movie-form\"]/div[2]/div[2]/div/div/div[1]/div/input")).setValue(movie.title);
        this.selectStatus();
        $(By.xpath("//*[@id=\"movie-form\"]/div[2]/div[3]/div/div/div[1]/div/input")).setValue(movie.year.toString());
        $(By.xpath("//*[@id=\"movie-form\"]/div[2]/div[3]/div/div/div[2]/div/input")).setValue(movie.releaseDate);
        this.inputCast(movie.cast);
        $(By.xpath("//*[@id=\"movie-form\"]/div[2]/div[5]/div/textarea")).setValue(movie.plot);
        this.upload(movie.cover);
        this.cadastra();

        return this;
    }

    public ElementsCollection items() {
        return $$(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/div/div[2]/div[1]/table/tbody/tr"));
    }

    private void upload(File cover) {
        $(By.xpath("//*[@id=\"upcover\"]")).uploadFile(cover);
    }

    private void inputCast(List<String> cast) {
        SelenideElement element = $(By.xpath("//*[@id=\"movie-form\"]/div[2]/div[4]/div/div/div/input"));

        for (String actor : cast) {
            element.setValue(actor);
            element.sendKeys(Keys.TAB);
        }
    }

    private void selectStatus() {
        $(By.xpath("//*[@id=\"movie-form\"]/div[2]/div[2]/div/div/div[2]/div/div[1]/input")).click();
        $(By.xpath("/html/body/div[2]/div[1]/div[1]/ul/li[2]/span")).click();

    }

    private void cadastra() {
        $(By.xpath("//*[@id=\"create-movie\"]")).click();
    }
}

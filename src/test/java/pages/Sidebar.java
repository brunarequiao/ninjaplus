package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Sidebar {
    public SelenideElement loggedUser() {
        return $(By.xpath(("/html/body/div/div[2]/div[1]/div[2]/div[1]/div[2]/a/span")));
    }
}

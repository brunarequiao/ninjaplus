package tests;

import commom.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;

public class LoginTests extends BaseTest {


    @DataProvider(name = "login-alerts")
    public Object[][] loginProvider() {
        return new Object[][]{
                {"bruna@teste.com", "pwd1234", "Usuário e/ou senha inválidos"},
                {"bruna1@teste.com", "pwd123", "Usuário e/ou senha inválidos"},
                {"", "pwd123", "Opps. Cadê o email?"},
                {"bruna@teste.com", "", "Opps. Cadê a senha?"}
        };
    }

    @Test
    public void shouldSeeLoggedUser() {

        Login
                .open()
                .with("bruna@teste.com", "pwd123");

        side.loggedUser().shouldHave(text("Bruna"));
    }


    @Test(dataProvider = "login-alerts")
    public void shouldSeeLoginAlerts(String email, String pass, String expectAlert) {

        Login
                .open()
                .with(email, pass)
                .alert().shouldHave(text(expectAlert));

    }

    @AfterMethod
    public void cleanUp() {
        Login.clearSession();
    }

}

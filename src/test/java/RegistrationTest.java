import com.codeborne.selenide.Configuration;
import org.junit.Before;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RegisterPage;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import static pageObject.LinkUrl.BASE_URL;
import static com.codeborne.selenide.Selenide.*;
import static pageObject.LinkUrl.*;

public class RegistrationTest {

    String email = RandomStringUtils.randomAlphabetic(6) + "@yandex.ru";
    String password = RandomStringUtils.randomAlphabetic(6);
    String name = RandomStringUtils.randomAlphabetic(6);

    @Before
    public void setBrowser(){
        Configuration.startMaximized = true;
        // Для MacOS
        //System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver");

        // Для Windows
        // System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver.exe");
    }

    @Test
    @DisplayName("Проверка успешной регистрации.")
    public void сheckSuccessfulRegistration () {

        MainPage mainPage = open(BASE_URL, MainPage.class);
        mainPage.clickPersonalAreaButton();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = page(RegisterPage.class);
        registerPage.fillFormRegister(name, email, password);

        //mainPage.checkPageDisplay("Вход", LOGIN_PAGE_URL);
        loginPage.headingSearchLogin();
        mainPage.checkAssertEqualsUrlTrue(LOGIN_PAGE_URL);
    }

    @Test
    @DisplayName("Проверка, что отображается ошибка для некорректного пароля.")
    public void сheckErrorRegistrationForIncorrectPassword () {
        RegisterPage registerPage = open(REGISTER_PAGE_URL, RegisterPage.class);
        registerPage.fillFormRegister(name, email, "123");

        registerPage.сheckErrorPasswordRegister();
    }
}

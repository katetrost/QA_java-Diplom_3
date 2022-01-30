import com.UserOperations;
import com.codeborne.selenide.Configuration;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RegisterPage;
import pageObject.RestorePasswordPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static pageObject.LinkUrl.*;

public class LoginTest {
    private UserOperations userOperations;

    @After
    public void deleteUser() {
        userOperations.delete();
    }

    @Before
    public void setBrowser(){
        Configuration.startMaximized = true;
        // Для MacOS
        //System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver");

        // Для Windows
        // System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver.exe");
    }

    @Before
    public void setUp() {
        userOperations = new UserOperations();
    }

    @Test
    @DisplayName("Проверка входа через кнопку «Личный кабинет»")
    public void сheckLoginOnPersonalAreaButton () {
        //регистрация нового пользователя
        Map<String, String> credentials = userOperations.register();

        MainPage mainPage = open(BASE_URL, MainPage.class);
        mainPage.clickPersonalAreaButton();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillFormLogin(credentials.get("email"), credentials.get("password"));

        //проверка, что после успешного входа, происходит переход на главную страницу
        mainPage.checkPageDisplay("Оформить заказ", BASE_URL);
    }

    @Test
    @DisplayName("Проверка входа по кнопке «Войти в аккаунт» на главной")
    public void сheckLoginOnSignInButton() {
        Map<String, String> credentials = userOperations.register();

        MainPage mainPage = open(BASE_URL, MainPage.class);
        mainPage.clickSignInButton();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillFormLogin(credentials.get("email"), credentials.get("password"));

        //проверка что после входа происходит переход на главную страницу
        mainPage.checkPageDisplay("Оформить заказ", BASE_URL);
    }

    @Test
    @DisplayName("Проверка входа через кнопку в форме регистрации")
    public void сheckLoginOnFormRegister () {
        Map<String, String> credentials = userOperations.register();

        RegisterPage registerPage = open(REGISTER_PAGE_URL, RegisterPage.class);
        registerPage.clickSignInLink();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillFormLogin(credentials.get("email"), credentials.get("password"));

        MainPage mainPage = page(MainPage.class);
        mainPage.checkPageDisplay("Оформить заказ", BASE_URL);
    }

    @Test
    @DisplayName("Проверка входа через кнопку в форме восстановления пароля")
    public void сheckLoginOnFormPasswordRecovery () {
        Map<String, String> credentials = userOperations.register();

        RestorePasswordPage restorePasswordPage = open(RESTORE_PAGE_URL, RestorePasswordPage.class);
        restorePasswordPage.clickSignInLinkFormRestore();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillFormLogin(credentials.get("email"), credentials.get("password"));

        MainPage mainPage = page(MainPage.class);
        mainPage.checkPageDisplay("Оформить заказ", BASE_URL);
    }
}

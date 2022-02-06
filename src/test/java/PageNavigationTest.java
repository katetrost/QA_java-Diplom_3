import com.UserOperations;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.ProfilePage;

import java.util.Map;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static pageObject.LinkUrl.*;

public class PageNavigationTest {
    private UserOperations userOperations;

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

    @After
    public void deleteUser() {
        userOperations.delete();
    }

    @Test
    @DisplayName("Переход в личный кабинет с авторизацией.")
    @Description("Проверь переход на страницу «Профиль» по клику на «Личный кабинет».")
    public void сheckGoToPersonalAccountWithAuthorizationTest() {
        //регистрация нового пользователя
        Map<String, String> credentials = userOperations.register();
        //вход под пользователем
        LoginPage loginPage = open(LOGIN_PAGE_URL, LoginPage.class);
        loginPage.fillFormLogin(credentials.get("email"), credentials.get("password"));

        //клик на «Личный кабинет»
        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAreaButton();

        //проверка перехода на страницу "Профиль"
        mainPage.checkPageDisplay("Профиль", PROFILE_PAGE_URL);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор с авторизации.")
    @Description("Проверь переход по клику на «Конструктор».")
    public void сheckGoToFromLoginPageInConstructorWithAuthorizationTest() {
        //регистрация нового пользователя
        Map<String, String> credentials = userOperations.register();
        //вход под пользователем
        LoginPage loginPage = open(LOGIN_PAGE_URL, LoginPage.class);
        loginPage.fillFormLogin(credentials.get("email"), credentials.get("password"));
        //открываем страницу личного кабинета, страница Профиль
        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAreaButton();

        //клик на «Конструктор»
        loginPage.clickConstructorLink();

        //проверка, что произошел перехода на главную страницу по клику на логотип
        mainPage.checkPageDisplay("Соберите бургер", BASE_URL);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип с авторизации.")
    @Description("Проверь переход по клику на логотип Stellar Burgers.")
    public void сheckClickLogoGoToFromLoginPageInConstructorWithAuthorizationTest() {
        //регистрация нового пользователя
        Map<String, String> credentials = userOperations.register();
        //вход под пользователем
        LoginPage loginPage = open(LOGIN_PAGE_URL, LoginPage.class);
        loginPage.fillFormLogin(credentials.get("email"), credentials.get("password"));
        //переходим в личный кабинет, на страницу "Профиль"
        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAreaButton();

        //клик по Лого
        loginPage.clickLogoLink();

        //проверка, что произошел перехода на главную страницу по клику на логотип
        mainPage.checkPageDisplay("Соберите бургер", BASE_URL);
    }

}

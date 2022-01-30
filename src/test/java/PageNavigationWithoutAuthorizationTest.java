import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pageObject.LoginPage;
import pageObject.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static pageObject.LinkUrl.BASE_URL;
import static pageObject.LinkUrl.LOGIN_PAGE_URL;

public class PageNavigationWithoutAuthorizationTest {
    @Before
    public void setBrowser(){
        Configuration.startMaximized = true;
        // Для MacOS
        //System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver");

        // Для Windows
        // System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver.exe");
    }

    @Test
    @DisplayName("Переход в личный кабинет без авторизации.")
    @Description("Проверь переход на страницу «Вход» по клику на «Личный кабинет».")
    public void сheckGoToPersonalAccountWithoutAuthorizationTest() {
        //открываем главную страницу
        MainPage mainPage = open(BASE_URL, MainPage.class);
        //клик на «Личный кабинет»
        mainPage.clickPersonalAreaButton();

        //проверка перехода на страницу "Вход"
        mainPage.checkPageDisplay("Вход", LOGIN_PAGE_URL);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор без авторизации.")
    @Description("Проверь переход по клику на «Конструктор».")
    public void сheckGoToFromLoginPageInConstructorWithoutAuthorizationTest() {
        //открываем страницу личного кабинета, страница Входа
        LoginPage loginPage = open(LOGIN_PAGE_URL, LoginPage.class);

        //клик на «Конструктор»
        loginPage.clickConstructorLink();

        //проверка, что произошел перехода на главную страницу по клику на логотип
        MainPage mainPage = page(MainPage.class);
        mainPage.checkPageDisplay("Соберите бургер", BASE_URL);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип без авторизации.")
    @Description("Проверь переход по клику на логотип Stellar Burgers.")
    public void сheckClickLogoGoToFromLoginPageInConstructorWithoutAuthorizationTest() {
        //открываем личного кабинет, страницу Вход
        LoginPage loginPage = open(LOGIN_PAGE_URL, LoginPage.class);

        //клик по Лого
        loginPage.clickLogoLink();

        //проверка, что произошел перехода на главную страницу по клику на логотип
        MainPage mainPage = page(MainPage.class);
        mainPage.checkPageDisplay("Соберите бургер", BASE_URL);
    }
}

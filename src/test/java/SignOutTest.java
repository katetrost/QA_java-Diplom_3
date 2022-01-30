import com.UserOperations;
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

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static pageObject.LinkUrl.LOGIN_PAGE_URL;

public class SignOutTest {
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
    @DisplayName("Выход из аккаунта.")
    @Description("Проверь выход по кнопке «Выйти» в личном кабинете.")
    public void сheckSignOutClickExitButtonTest() {
        //регистрация нового пользователя
        Map<String, String> credentials = userOperations.register();
        //вход под пользователем
        LoginPage loginPage = open(LOGIN_PAGE_URL, LoginPage.class);
        loginPage.fillFormLogin(credentials.get("email"), credentials.get("password"));
        //переходим в личный кабинет, на страницу "Профиль"
        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAreaButton();

        //клик по кнопке Выход
        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.clickExitButton();

        //После выхода из аккаунта отображается страница Входа
        mainPage.checkPageDisplay("Вход", LOGIN_PAGE_URL);
    }
}

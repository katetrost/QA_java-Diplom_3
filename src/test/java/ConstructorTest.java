import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pageObject.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static pageObject.LinkUrl.BASE_URL;

public class ConstructorTest {

    @Before
    public void setBrowser(){
        Configuration.startMaximized = true;
        // Для MacOS
        //System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver");

        // Для Windows
        // System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver.exe");
    }

    @Test
    @DisplayName("Проверка, что работает переход к разделу «Булки».")
    @Description("Переход к разделу «Булки» по клику на слово Булки.")
    public void сheckGoToTheBunSectionTest() {
        //открываем главную страницу
        MainPage mainPage = open(BASE_URL, MainPage.class);
        mainPage.clickFillingButton();

        //клик на слово Булки
        mainPage.clickBunButton();

        //проверка, что отображаются булочки
        mainPage.checkBunSectionText();
    }

    @Test
    @DisplayName("Проверка, что работает переход к разделу «Соусы».")
    @Description("Переход к разделу «Соусы» по клику на слово Соусы.")
    public void сheckGoToTheSauceSectionTest() {
        //открываем главную страницу
        MainPage mainPage = open(BASE_URL, MainPage.class);

        //клик на слово Соусы
        mainPage.clickSauceButton();

        //проверка, что отображаются булочки
        mainPage.checkSaucesSectionText();
    }

    @Test
    @DisplayName("Проверка, что работает переход к разделу «Начинки».")
    @Description("Переход к разделу «Начинки» по клику на слово Начинки.")
    public void сheckGoToTheFillingSectionTest() {
        //открываем главную страницу
        MainPage mainPage = open(BASE_URL, MainPage.class);

        //клик на слово Соусы
        mainPage.clickFillingButton();

        //проверка, что отображаются булочки
        mainPage.checkFillingSectionText();
    }
}

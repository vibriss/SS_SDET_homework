import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.YandexPage;
import utils.LetterContent;

import java.util.concurrent.TimeUnit;

public class YandexMailTest {

    private static WebDriver driver;
    private static YandexPage yandexPage;

    @BeforeClass
    public static void prepareTestEnvironment(){
        driver = new ChromeDriver();
        driver.get("http://yandex.ru");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        yandexPage = new YandexPage(driver);
        yandexPage.login("sdet.kotov", "sdettest");
        yandexPage.getMailbox();
    }

    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }

    @Test
    public void givenSendNewLetterWithProvidedTheme_whenExpectIncrementLettersCountWithProvidedTheme_thenSuccess(){
        int lettersCountBefore = yandexPage.getLettersCountWithTheme("Simbirsoft theme");

        yandexPage.writeNewLetter("sdet.kotov@yandex.ru", "Simbirsoft theme", LetterContent.compose(lettersCountBefore));

        yandexPage.waitForNewLetters();

        int lettersCountAfter = yandexPage.getLettersCountWithTheme("Simbirsoft theme");

        Assert.assertEquals(lettersCountAfter, lettersCountBefore + 1);
    }
}

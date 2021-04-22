import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.MailPage;
import utils.LetterContent;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class YandexMailUITest {

    private static WebDriver driver;
    private static MailPage mailPage;
    private static String mailPageUrl;
    private static String login;
    private static String password;
    private static String mailTo;
    private static String subject;

    @BeforeClass
    public static void prepareTestEnvironment() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/test.properties"));

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setPlatform(Platform.ANY);
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("90.0.4430.72");

        mailPageUrl = properties.getProperty("mailPageUrl");
        login = properties.getProperty("login");
        password = properties.getProperty("password");
        mailTo = properties.getProperty("mailTo");
        subject = properties.getProperty("subject");

        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        driver.get(mailPageUrl);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        mailPage = new MailPage(driver);
        mailPage.getAuthorizationPage().authorize(login, password);
    }

    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }

    @Test
    public void givenSendNewLetterWithProvidedTheme_whenExpectIncrementLettersCountWithProvidedTheme_thenSuccess(){
        long lettersCountBefore = mailPage.getLettersCountWithTheme(subject);
        mailPage.writeNewLetter(mailTo, subject, LetterContent.compose(lettersCountBefore));
        mailPage.waitForNewLetters();
        long lettersCountAfter = mailPage.getLettersCountWithTheme(subject);

        Assert.assertEquals(lettersCountAfter, lettersCountBefore + 1);
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class YandexPage {

    protected WebDriver driver;

    public YandexPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String login, String password){
        WebElement authorizeButton = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div[1]/div/div/a[1]"));
        authorizeButton.click();

        WebElement loginField = driver.findElement(By.xpath("//*[@id=\"passp-field-login\"]"));
        loginField.sendKeys(login);
        loginField.submit();

        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"passp-field-passwd\"]"));
        passwordField.sendKeys(password);
        passwordField.submit();
    }

    public void getMailbox() {
        WebElement mailBoxButton = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div[1]/div[1]/div[2]/div[1]/a/div[1]"));
        mailBoxButton.click();

        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(windows.get(1));
    }

    public int getLettersCountWithTheme(String theme) {
        List<WebElement> letters = driver.findElements(By.xpath("//span[@title='" + theme + "']"));

        return letters.size();
    }

    public void writeNewLetter(String mailTo, String theme, String content) {
        WebElement writeNewLetterButton = driver.findElement(By.xpath("//a[@title='Написать (w, c)']"));
        writeNewLetterButton.click();

        WebElement mailToField = driver.findElement(By.xpath("//*[@id=\"nb-1\"]/body/div[2]/div[10]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div[1]/div/div[1]/div[1]/div[1]/div/div/div/div/div"));
        mailToField.sendKeys(mailTo);

        WebElement themeField = driver.findElement(By.xpath("//*[@id=\"nb-1\"]/body/div[2]/div[10]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div[1]/div/div[1]/div[1]/div[3]/div/div/input"));
        themeField.sendKeys(theme);

        WebElement contentField = driver.findElement(By.xpath("//*[@id=\"cke_1_contents\"]/div"));
        contentField.sendKeys(content);

        WebElement sendButton = driver.findElement(By.xpath("//*[@id=\"nb-1\"]/body/div[2]/div[10]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div[2]/div/div[1]/div[1]/button"));
        sendButton.click();

        WebElement returnLink = driver.findElement(By.xpath("//*[@id=\"nb-1\"]/body/div[13]/div/div[1]/div[2]/a"));
        returnLink.click();
    }

    public void waitForNewLetters(){
        WebElement refreshButton = driver.findElement(By.xpath("//*[@id=\"nb-1\"]/body/div[2]/div[7]/div/div[3]/div[2]/div[2]/div/div/span"));
        refreshButton.click();

        WebElement loadingBar = driver.findElement(By.xpath("//*[@id=\"nb-1\"]/body/div[2]/div[6]/div"));
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.attributeToBe(loadingBar, "width", "0px"));
    }
}

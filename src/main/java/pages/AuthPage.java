package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthPage {

    private WebDriver driver;

    @FindBy(id = "passp-field-login")
    private WebElement loginField;

    @FindBy(id = "passp-field-passwd")
    private WebElement passwordField;

    public AuthPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public MailPage authorize(String login, String password) {
        loginField.sendKeys(login);
        loginField.submit();
        passwordField.sendKeys(password);
        passwordField.submit();

        return new MailPage(this.driver);
    }
}

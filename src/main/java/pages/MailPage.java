package pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailPage {

    private WebDriver driver;

    @FindBy(className = "HeadBanner-Button-Enter")
    private WebElement loginButton;

    @FindBy(className = "mail-ComposeButton")
    private WebElement writeNewLetterButton;

    @FindBy(className = "composeYabbles")
    private WebElement mailToField;

    @FindBy(className = "ComposeSubject-TextField")
    private WebElement themeField;

    @FindBy(className = "cke_wysiwyg_div")
    private WebElement contentField;

    @FindBy(className = "ComposeControlPanelButton")
    private WebElement sendButton;

    @FindBy(className = "ComposeDoneScreen-Link")
    private WebElement returnLink;

    @FindBy(className = "mail-ComposeButton-Refresh")
    private WebElement refreshButton;

    @FindBy(className = "mail-LoadingBar")
    private WebElement loadingBar;

    @FindAll(@FindBy(className = "mail-MessageSnippet-Item_subject"))
    List<WebElement> subjects;

    public MailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public AuthPage getAuthorizationPage() {
        loginButton.click();
        return new AuthPage(this.driver);
    }

    public long getLettersCountWithTheme(String subject) {
        return subjects.stream().filter(x -> x.getText().equals(subject)).count();
    }

    public void writeNewLetter(String mailTo, String theme, String content) {
        writeNewLetterButton.click();
        mailToField.sendKeys(mailTo);
        themeField.sendKeys(theme);
        contentField.sendKeys(content);
        sendButton.click();
        returnLink.click();
    }

    public void waitForNewLetters(){
        refreshButton.click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.attributeToBe(loadingBar, "width", "0px"));
    }
}

package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    public static final String login = System.getProperties().getProperty("login");
    public static final String password = System.getProperties().getProperty("password");

    private final WebDriver driver;
    private Logger logger = LogManager.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = "body > div.body-wrapper > div > header.header2.header2_non-auth > div > div.header2__right > div.header2__auth-container > button")
    public WebElement ButtonEntrance;

    @FindBy(css = "div.new-input-line_slim:nth-child(3) > input:nth-child(1)")
    public WebElement loginInput;

    @FindBy(css = ".js-psw-input")
    public WebElement passwordInput;

    @FindBy(css = "div.new-input-line_last:nth-child(5) > button:nth-child(1)")
    public WebElement submitForm;

    public void auth() {

        ButtonEntrance.click();
        loginInput.sendKeys(login);
        logger.info("Введен логин");
        passwordInput.sendKeys(password);
        logger.info("Введен пароль");
        submitForm.submit();
        logger.info("Авторизация успешна");
    }

}

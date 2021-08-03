package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactsPage {

    private final WebDriver driver;
    private Logger logger = LogManager.getLogger(ContactsPage.class);

    public void enterLK() throws InterruptedException {
        Thread.sleep(1000);
        driver.get("https://otus.ru/lk/biography/personal/");
        logger.info("Открыт личный кабинет");


    }

    public void clearPersonalData() {
        name.clear();
        nameLatin.clear();
        lastName.clear();
        lastNameLatin.clear();
        dateOfBirth.clear();
    }

    public void fillInPersonalData(String nameF, String nameL, String nameLast, String nameLastLatin, String birthD) {
        name.sendKeys(nameF);
        nameLatin.sendKeys(nameL);
        lastName.sendKeys(nameLast);
        lastNameLatin.sendKeys(nameLastLatin);
        dateOfBirth.sendKeys(birthD);
    }


    @FindBy(id = "id_fname")
    public WebElement name;

    @FindBy(id = "id_fname_latin")
    public WebElement nameLatin;

    @FindBy(id = "id_lname")
    public WebElement lastName;

    @FindBy(id = "id_lname_latin")
    public WebElement lastNameLatin;

    @FindBy(css = ".input-icon > input:nth-child(1)")
    public WebElement dateOfBirth;

    @FindBy(css = ".js-lk-cv-dependent-master > label:nth-child(1) > div:nth-child(2)")
    public WebElement country;

    @FindBy(xpath = "//*[contains(text(), 'Россия')]")
    public WebElement countryRussia;

    @FindBy(css = ".js-lk-cv-dependent-slave-city > label:nth-child(1) > div:nth-child(2)")
    public WebElement city;

    @FindBy(xpath = "//*[contains(text(), 'Москва')]")
    public WebElement cityMoscow;

    @FindBy(css = "div.container__col_12:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > div:nth-child(2)")
    public WebElement englishLevel;

    @FindBy(xpath = "//*[contains(text(), 'Начальный уровень (Beginner)')]")
    public WebElement englishLevelBeginner;

    @FindBy(xpath = "//*[contains(text(), 'Сохранить и продолжить')]")
    public WebElement saveButton;


    public ContactsPage(WebDriver driver) {
        this.driver = driver;
    }


}

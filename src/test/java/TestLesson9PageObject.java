import constants.OtusWebsiteContactInformation;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ContactsPage;
import pages.LoginPage;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class TestLesson9PageObject {


    private Logger logger = LogManager.getLogger(TestLesson9PageObject.class);
    protected WebDriver driver;
    private Instant wait;

    @Before
    public void StartUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @After
    public void End() {
        if (driver != null)
            driver.quit();
    }


    @Test
    public void personalDataOfOtus() throws InterruptedException {
        ContactsPage contactsPage = PageFactory.initElements(driver, ContactsPage.class);
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);


        //1. Открыть otus.ru
        driver.get(("https://otus.ru"));
        logger.info("Открыта страница Отус");

        driver.manage().window().setSize(new Dimension(1900,800));
        logger.info("Открыто окно браузера 1900*800");

        //2. Авторизоваться на сайте
        loginPage.auth();
        //3. Войти в личный кабинет
        contactsPage.enterLK();
        //4. В разделе "О себе" заполнить все поля "Личные данные"
        contactsPage.clearPersonalData();

        contactsPage.name.sendKeys(OtusWebsiteContactInformation.NAME);
        contactsPage.nameLatin.sendKeys(OtusWebsiteContactInformation.NAME_LATIN);
        contactsPage.lastName.sendKeys(OtusWebsiteContactInformation.LAST_NAME);
        contactsPage.lastNameLatin.sendKeys(OtusWebsiteContactInformation.LAST_NAME_LATIN);
        contactsPage.dateOfBirth.sendKeys(OtusWebsiteContactInformation.DATE_OF_BIRTH);
        //Страна
        if(!contactsPage.country.getText().contains(OtusWebsiteContactInformation.COUNTRY))
        {
            contactsPage.country.click();
            contactsPage.countryRussia.click();
        }
        //Город
        if(!contactsPage.city.getText().contains(OtusWebsiteContactInformation.CITY))
        {
            contactsPage.city.click();
            contactsPage.cityMoscow.click();
        }
        //Уровень английского
        if(!contactsPage.englishLevel.getText().contains(OtusWebsiteContactInformation.ENGLISH_LEVEL))
        {
            contactsPage.englishLevel.click();
            contactsPage.englishLevelBeginner.click();
        }

        //5. Нажать сохранить
        contactsPage.saveButton.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlToBe("https://otus.ru/lk/biography/skills/"));
        //6. Открыть https://otus.ru в “чистом браузере”
        driver.quit();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        ContactsPage contactsPageAfterFilling = PageFactory.initElements(driver, ContactsPage.class);
        LoginPage loginPageAfterFilling = PageFactory.initElements(driver, LoginPage.class);

        logger.info("Драйвер поднят");
        driver.get(("https://otus.ru"));

        //7. Авторизоваться на сайте
        loginPageAfterFilling.auth();
        //8. Войти в личный кабинет
        contactsPageAfterFilling.enterLK();
        //9. Проверить, что в разделе о себе отображаются указанные ранее данные
        System.out.println("Привет!");
        Assert.assertEquals( "Ошибка в имени", OtusWebsiteContactInformation.NAME, contactsPageAfterFilling.name.getAttribute("value"));
        Assert.assertEquals("Ошибка в имени на латинском", OtusWebsiteContactInformation.NAME_LATIN, contactsPageAfterFilling.nameLatin.getAttribute("value"));
        Assert.assertEquals("Ошибка в фамилии", OtusWebsiteContactInformation.LAST_NAME, contactsPageAfterFilling.lastName.getAttribute("value"));
        Assert.assertEquals("Ошибка в фамилии на латинском", OtusWebsiteContactInformation.LAST_NAME_LATIN, contactsPageAfterFilling.lastNameLatin.getAttribute("value"));
        Assert.assertEquals("Ошибка в дате рождения", OtusWebsiteContactInformation.DATE_OF_BIRTH, contactsPageAfterFilling.dateOfBirth.getAttribute("value"));
        Assert.assertEquals("Ошибка в стране", OtusWebsiteContactInformation.COUNTRY, contactsPageAfterFilling.country.getText());
        Assert.assertEquals("Ошибка в городе", OtusWebsiteContactInformation.CITY, contactsPageAfterFilling.city.getText());
        Assert.assertEquals("Ошибка в уровне англ.", OtusWebsiteContactInformation.ENGLISH_LEVEL, contactsPageAfterFilling.englishLevel.getText());
    }

}




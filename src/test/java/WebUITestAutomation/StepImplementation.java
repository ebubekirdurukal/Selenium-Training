package WebUITestAutomation;

import base.BaseTest;
import com.sun.xml.internal.bind.v2.model.core.ElementInfo;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import static org.assertj.core.api.Assertions.assertThat;
import base.BaseTest;
import java.util.List;
import java.util.logging.Logger;

public class StepImplementation extends BaseTest {

    private final static Logger logger = Logger.getLogger(StepImplementation.class.getName());
    JavascriptExecutor js = (JavascriptExecutor) driver;
    private static By girisYapButonu = By.xpath("//a[contains(text(),'Yap')]");
    private static By emailAlani = By.xpath("//input[@id='Email']");
    private static By şifreAlani = By.xpath("//input[@id='Password']");
    private static By submitButonu = By.xpath("//button[@class='red-action-button submit-btn']");
    private static By cerezleriKabulEtButonu = By.xpath("//button[contains(text(),'KABUL ET')]");
    private static By bedavaHizmetKazan=By.xpath("//a[@class='blue bold']");
    private static By arkadasEmailAdresi=By.xpath("//div[@class='component']//div//div[1]//input[1]");
    private static By paylasButonu=By.xpath("//button[@class='red-action-button share-btn']");


    @Step("<email> ve <password> bilgileri ile hesaba giriş yap")
    public void girisYap(String email, String password) throws IOException {

        wait.until(ExpectedConditions.presenceOfElementLocated(girisYapButonu));
        driver.findElement(girisYapButonu).click();
        logger.info("giriş yap butonuna tıklandı");
        wait.until(ExpectedConditions.presenceOfElementLocated(emailAlani));
        WebElement emailalani = driver.findElement(emailAlani);
        emailalani.sendKeys(email);
        WebElement sifrealani = driver.findElement(şifreAlani);
        sifrealani.sendKeys(password);
        WebElement submitbutonu = driver.findElement(emailAlani);
        js.executeScript("arguments[0].scrollIntoView();", submitbutonu);
        driver.findElement(submitButonu).click();
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("screenshots/lastlogin.png"));
    }

    @Step("<text> yazısı sayfada bulunuyor mu kontrol et")
    public void textControl(String text) throws InterruptedException, IOException {
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("screenshots/"+text+"_control.png"));
        Assert.assertTrue("Text not found!", list.size() > 0);
        logger.info(text+ " yazısı sayfada bulundu");

    }


    @Step("<text> elementini seç ve tıkla")
    public void clickElement(String text) throws IOException {
        List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
        list.get(0).click();
        logger.info(text+" elementine tıklandı");
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("screenshots/"+text+"click.png"));

    }


    @Step("Sayfa çerezlerini Kabul et")
    public void acceptCookies() throws IOException {
        wait.until(ExpectedConditions.presenceOfElementLocated(cerezleriKabulEtButonu));
        driver.findElement(cerezleriKabulEtButonu).click();
        logger.info("Çerezler kabul edildi");
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("screenshots/accepting_cookies.png"));
    }

    @Step("<address> adresine git")
    public void goToURL(String address) throws IOException {
        driver.get(address);
        logger.info(address+" adresine gidildi");
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("screenshots/address_control.png"));
    }

    @Step("<address> adresine hizmet öner")
    public void recommend(String address){
        wait.until(ExpectedConditions.presenceOfElementLocated(bedavaHizmetKazan));
        driver.findElement(bedavaHizmetKazan).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(arkadasEmailAdresi));
        WebElement arkadasemailadresi = driver.findElement(arkadasEmailAdresi);
        js.executeScript("arguments[0].scrollIntoView();", arkadasemailadresi);
        arkadasemailadresi.sendKeys(address);
        driver.findElement(paylasButonu).click();

    }



}

package hcyclewala.pageobjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import hcyclewala.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;
    public CheckoutPage(WebDriver driver){
        super(driver);
        this.driver = driver;       
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement ContryInput;

    @FindBy(css = ".list-group button")
    List<WebElement> contries;

    @FindBy(css = ".action__submit")
    WebElement submitOrderEle;

    public void selectContry(String halfName, String fullName)
    {
        // wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@placeholder='Select Country']"))));
    waitForElementClickable(ContryInput);
    ContryInput.sendKeys(halfName);
    WebElement selectedContry = contries.stream().filter(contry-> contry.getText().equals(fullName)).findFirst().orElse(null);
    selectedContry.click();
    }

    public ConfirmationPage SubmitOrder()
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", submitOrderEle);

        ConfirmationPage cnfPg = new ConfirmationPage(driver);
        return cnfPg;
    }
}

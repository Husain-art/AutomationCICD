package hcyclewala.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import hcyclewala.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
    WebDriver driver;
public LandingPage(WebDriver driver)
{
    super(driver);
    this.driver = driver;
    PageFactory.initElements(driver, this);
}

@FindBy(id="userEmail")
WebElement userEmail;

@FindBy(id="userPassword")
WebElement password;

@FindBy(id="login")
WebElement submit;

@FindBy(css="[class*='flyInOut']")
WebElement errorMessage;

public void goTo()
{
    driver.get("https://rahulshettyacademy.com/client/");
}

public ProductCatalogue LoginApplication(String email, String userPassword)
{
    userEmail.sendKeys(email);
    password.sendKeys(userPassword);
    submit.click();
    ProductCatalogue pc = new ProductCatalogue(driver);
    return pc;
}

public String getErrorMessage()
{
    waitForWebElementToAppear(errorMessage);
    return errorMessage.getText();
}

}

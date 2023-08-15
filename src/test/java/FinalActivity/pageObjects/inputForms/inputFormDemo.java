package FinalActivity.pageObjects.inputForms;

import FinalActivity.resources.abstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class inputFormDemo extends abstractComponents {
    WebDriver driver;

    public inputFormDemo(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void goTo(){
        driver.get("https://demo.seleniumeasy.com/input-form-demo.html");
    }

    @FindBy (xpath = "//input[@name='first_name']") WebElement txtFirstName;
    @FindBy (xpath = "//input[@name='last_name']") WebElement txtLastName;
    @FindBy (xpath = "//input[@name='email']") WebElement txtEmail;
    @FindBy (xpath = "//input[@name='phone']") WebElement txtPhoneNum;
    @FindBy (xpath = "//input[@name='address']") WebElement txtAddress;
    @FindBy (xpath = "//input[@name='city']") WebElement txtCity;
    @FindBy (xpath = "//input[@name='zip']") WebElement txtZipCode;
    @FindBy (xpath = "//input[@name='website']") WebElement txtWebsite;
    @FindBy (xpath = "//textarea") WebElement txtProjDesc;
    @FindBy (xpath = "//select[@name='state']") WebElement drpdnState;
    @FindBy (css = ".btn.btn-default") WebElement btnSubmit;

    public void typeData(String firstName, String lastName, String email, String phoneNum, String address,
                         String city, String zipCode, String webDomain, String projDesc ){
        txtFirstName.sendKeys(firstName);
        txtLastName.sendKeys(lastName);
        txtEmail.sendKeys(email);
        txtPhoneNum.sendKeys(phoneNum);
        txtAddress.sendKeys(address);
        txtCity.sendKeys(city);
        txtZipCode.sendKeys(zipCode);
        txtWebsite.sendKeys(webDomain);
        txtProjDesc.sendKeys(projDesc);
    }

    public void selectState(String state){
        Select se = new Select(drpdnState);
        se.selectByVisibleText(state);
    }

    public void selectHosting(String option){
        click(driver.findElement(By.xpath("//input[@value='" + option + "']")));
    }

    public void submitForm(){
        click(btnSubmit);
    }

}

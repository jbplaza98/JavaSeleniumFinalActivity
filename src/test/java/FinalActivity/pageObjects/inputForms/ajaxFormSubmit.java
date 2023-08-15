package FinalActivity.pageObjects.inputForms;

import FinalActivity.resources.abstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ajaxFormSubmit extends abstractComponents {
    WebDriver driver;

    public ajaxFormSubmit(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void goTo(){
        driver.get("https://demo.seleniumeasy.com/ajax-form-submit-demo.html");
    }

    @FindBy (id = "title")
    WebElement txtName;
    @FindBy (id = "description")
    WebElement txtComment;
    @FindBy (id = "btn-submit")
    WebElement btnSubmit;
    @FindBy (xpath = "//div[contains(text(),'Form submited Successfully!')]")
    WebElement txtSubmitted;

    public void setData(String name, String comment){
        txtName.sendKeys(name);
        txtComment.sendKeys(comment);
    }

    public String submitForm(){
        click(btnSubmit);
        waitForWebElementToAppear(txtSubmitted);
        return txtSubmitted.getText();
    }


}

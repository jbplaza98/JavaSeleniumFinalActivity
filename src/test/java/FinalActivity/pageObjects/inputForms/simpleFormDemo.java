package FinalActivity.pageObjects.inputForms;

import FinalActivity.resources.abstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class simpleFormDemo extends abstractComponents {

    WebDriver driver;

    public simpleFormDemo(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void goTo(){
        driver.get("https://demo.seleniumeasy.com/basic-first-form-demo.html");
    }

    @FindBy (id = "user-message") WebElement txtboxMessage;
    @FindBy (xpath = "//button[contains(text(),'Show Message')]") WebElement btnMessage;
    @FindBy (id = "value1") WebElement txtboxValueA;
    @FindBy (id = "value2") WebElement txtboxValueB;
    @FindBy (xpath = "//button[contains(text(),'Get Total')]") WebElement btnTotal;
    @FindBy (id = "display") WebElement txtMessage;
    @FindBy (id = "displayvalue") WebElement txtTotal;

    public void singleInputField(String message){
        waitForWebElementToAppear(txtboxMessage);
        txtboxMessage.sendKeys(message);
        print("Typing message : " + message);
        click(btnMessage);
        print(btnMessage.getText() + " is clicked.");
    }

    public String twoInputField(String a, String b){
        txtboxValueA.sendKeys(a);
        print("Typing a : " + a);
        txtboxValueB.sendKeys(b);
        print("Typing b : " + b);
        click(btnTotal);
        print(btnTotal.getText() + "is clicked.");

        int total = Integer.parseInt(a) + Integer.parseInt(b);
        return String.valueOf(total);
    }

    public String getMessage(){
        waitForWebElementToAppear(txtMessage);
        return txtMessage.getText();
    }

    public String getTotal(){
        waitForWebElementToAppear(txtTotal);
        return txtTotal.getText();
    }
}













package FinalActivity.pageObjects.alertsModals;

import FinalActivity.resources.abstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class bootstrapAlerts extends abstractComponents {
    WebDriver driver;

    public bootstrapAlerts(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void goTo(){
        driver.get("https://demo.seleniumeasy.com/bootstrap-alert-messages-demo.html");
    }

    @FindBy (xpath = "(//div[@class='col-md-6'])[1]/div")
    List<WebElement> alertDialogs;
    @FindBy (xpath = "//div[@class='col-md-4']/button")
    List<WebElement> alertButtons;

    @FindBy (xpath = "(//div[@class='col-md-6'])[1]/div/button")
    List<WebElement> alertClose;

    public void clickButtons() throws InterruptedException {
        int counterClosebtn = 0;
        int secondsToWait;
        for(int i = 0; i < alertButtons.size(); i++){
            click(alertButtons.get(i));
            String btnName = alertButtons.get(i).getText();
            String alertMsg = alertDialogs.get(i).getText();

            print(btnName + " clicked. Alert says : " + alertMsg);
            waitForWebElementToAppear(alertDialogs.get(i));
            if (btnName.contains("Normal")){
                click(alertClose.get(counterClosebtn));
                counterClosebtn++;
                print(btnName + " close button clicked");
            } else {
                Thread.sleep(2000);
                secondsToWait = Integer.parseInt(alertMsg.substring(alertMsg.indexOf("seconds")-2,alertMsg.indexOf("seconds")-1));
                print("Waiting " + secondsToWait + " seconds for " + btnName + " to hide");
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secondsToWait));
                wait.until(ExpectedConditions.invisibilityOf(alertDialogs.get(i)));
            }
            if(alertDialogs.get(i).isDisplayed()==false){
                print(btnName + " dialog is now closed");
                divider();
            }
        }

    }




}

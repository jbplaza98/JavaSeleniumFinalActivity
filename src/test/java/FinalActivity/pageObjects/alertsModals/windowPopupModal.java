package FinalActivity.pageObjects.alertsModals;

import FinalActivity.resources.abstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class windowPopupModal extends abstractComponents {
    WebDriver driver;

    public windowPopupModal(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void goTo(){
        driver.get("https://demo.seleniumeasy.com/window-popup-modal-demo.html");
    }

    @FindBy (xpath = "//div[@class='panel-body']/div/a")
    List<WebElement> buttons;

    public void clickButtons(){
        for(WebElement button : buttons){
            print("**********************************************************************************");
            click(button);
            print("Clicked " + button.getText() + " button");

            waitForWindowsToAppear(driver.getWindowHandles().size());
            print("Waiting for windows to open");
            print("Windows opened : " + String.valueOf(driver.getWindowHandles().size()-1));
            switchWindow();
        }
    }

    public void switchWindow() {
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()){
            if (!originalWindow.contentEquals(windowHandle)){
                divider();
                driver.switchTo().window(windowHandle);
                print("Current link : " + driver.getCurrentUrl());
                print("Title : " + driver.getTitle());

                driver.close();
            }
        }
        driver.switchTo().window(originalWindow);
    }


}



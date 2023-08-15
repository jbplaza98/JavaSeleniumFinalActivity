package FinalActivity.pageObjects.inputForms;

import FinalActivity.resources.abstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class checkBoxDemo extends abstractComponents {
    WebDriver driver;

    public checkBoxDemo(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void goTo(){
        driver.get("https://demo.seleniumeasy.com/basic-checkbox-demo.html");
    }

    @FindBy (id = "check1")
    WebElement btnCheckAll;

    @FindBy (css = ".cb1-element")
    List<WebElement> checkboxes;

    public void clickCheckAllButton(){
        waitForWebElementToAppear(btnCheckAll);
        print(btnCheckAll.getAttribute("value") + " is clicked");
        click(btnCheckAll);
    }

    public void clickOption(){
        Random rand = new Random();
        int num = rand.nextInt(4);
        waitForWebElementsToAppear(checkboxes);
        click(checkboxes.get(num));
        num+=1;
        print("Option " + num + " is clicked");
    }

    public boolean getCheckboxStatus(){
        boolean flag = true;
        for (WebElement checkbox : checkboxes){
            if(!checkbox.isSelected())
                flag = false;
        }
        return flag;
    }

    public String getButtonStatus(){
        return btnCheckAll.getAttribute("value");
    }


}

package FinalActivity.pageObjects.inputForms;

import FinalActivity.resources.abstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class selectDropdownList extends abstractComponents {

    WebDriver driver;

    public selectDropdownList(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void goTo(){

        driver.get("https://demo.seleniumeasy.com/basic-select-dropdown-demo.html");
    }

    @FindBy (id = "select-demo") WebElement drpdnDay;
    @FindBy (css = "p.selected-value") WebElement txtDaySelected;
    @FindBy (id = "multi-select") WebElement drpdnMultiSelect;
    @FindBy (id = "printAll") WebElement btnGetAllSelected;
    @FindBy (id = "printMe") WebElement btnFirstSelected;
    @FindBy (css = "p.getall-selected") WebElement txtStateSelected;

    public void selectDay(String day){
        Select se = new Select(drpdnDay);
        se.selectByValue(day);
        print("Selected : " + day);
    }

    public String getSelectedDay(){
        waitForWebElementToAppear(txtDaySelected);
        return txtDaySelected.getText();
    }

    public void selectMultiStates(String[] states){
        print("Selecting Multiple States");
        Select se = new Select(drpdnMultiSelect);
        for (String state : states){
            se.selectByVisibleText(state);
            print("Selecting : " + state);
        }
    }

    public String getSelectedText(){
        return txtStateSelected.getText();
    }

    public void clickFirstSelectedButton(){
        click(btnFirstSelected);
    }

    public void clickGetAllSelected(){
        click(btnGetAllSelected);
    }
}

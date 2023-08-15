package FinalActivity.pageObjects.inputForms;

import FinalActivity.resources.abstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class jQuerySelectDropdown extends abstractComponents {
    WebDriver driver;

    public jQuerySelectDropdown(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void goTo(){
        driver.get("https://demo.seleniumeasy.com/jquery-dropdown-search-demo.html");
    }

    @FindBy (xpath = "(//div[@class='panel-body'])[2]/span")
    WebElement drpdnCountry;

    @FindBy (xpath ="(//span[@class='selection'])[2]/span")
    WebElement cmbobxStates;

    @FindBy (xpath = "(//span[@class='selection'])[3]/span")
    WebElement drpdnTerritories;

    @FindBy (id = "files")
    WebElement drpdnFiles;

    @FindBy (xpath = "(//input[@type='search'])[2]")
    WebElement txtbxCountry;

    @FindBy (xpath = "(//input[@class='select2-search__field'])[2]")
    WebElement txtbxTerritory;

    @FindBy (xpath = "//input[@type='search']")
    WebElement txtbxStates;

    public void searchCountry(String country){
        click(drpdnCountry);
        txtbxCountry.sendKeys(country);
        click(driver.findElement(By.xpath("//li[contains(text(),'" + country + "')]")));
    }

    public void selectStates(String[] states) throws InterruptedException {
        for (String state :states){
            click(cmbobxStates);
            txtbxStates.sendKeys(state);
            if(state.equalsIgnoreCase("Kansas"))
                txtbxStates.sendKeys(Keys.ARROW_DOWN);
            txtbxStates.sendKeys(Keys.ENTER);
        }
    }

    public void selectTerritory(String territory){
        drpdnTerritories.click();
        waitForWebElementToAppear(txtbxTerritory);
        txtbxTerritory.sendKeys(territory,Keys.ENTER);
    }

    public void selectFile(String file){
        Select se = new Select(drpdnFiles);
        se.selectByVisibleText(file);
    }
}

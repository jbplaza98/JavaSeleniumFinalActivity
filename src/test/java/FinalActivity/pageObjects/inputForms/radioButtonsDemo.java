package FinalActivity.pageObjects.inputForms;

import FinalActivity.resources.abstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class radioButtonsDemo extends abstractComponents {

    WebDriver driver;

    public radioButtonsDemo(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void goTo(){
        driver.get("https://demo.seleniumeasy.com/basic-radiobutton-demo.html");
    }

    @FindBy (id = "buttoncheck")
    WebElement btnCheckedValue;
    @FindBy (xpath = "//p[@class='radiobutton']")
    WebElement txtChecked;
    @FindBy (xpath = "//p[@class='groupradiobutton']")
    WebElement txtGroup;
    @FindBy (xpath = "//input[@name='ageGroup']")
    List<WebElement> rdbtnAgeGroup;
    @FindBy (xpath = "(//div[@class='panel panel-default'])[2]/div/label/input")
    List<WebElement> rdbtnSex;
    @FindBy (xpath = "(//div[@class='panel panel-default'])[3]/div/div[1]/label/input")
    List<WebElement> rdbtnGroupSex;
    @FindBy (xpath = "(//div[@class='panel panel-default'])[3]/div/div[2]/label/input")
    List<WebElement> rdbtnGroupAge;
    @FindBy (xpath = "//button[contains(text(),'Get values')]")
    WebElement btnGroupValue;


    public String getSelectedRadioBtn(List<WebElement> radiobuttons){
        String selected = "";
        for(WebElement e : radiobuttons.stream().filter(s->s.isSelected()==true).collect(Collectors.toList())){
            selected = e.getAttribute("value");
        }
        return selected;
    }


    public void selectRadioBtnOption(String option){
        rdbtnSex.stream().filter(s->s.getAttribute("value").equalsIgnoreCase(option)).peek(p->print("Clicking " + p.getAttribute("value"))).forEach(e->click(e));
    }

    public String getRadioBtnOption(){
        return getSelectedRadioBtn(rdbtnSex);
    }


    public void selectGrpRadioBtnOption(String sex, String age){
        rdbtnGroupSex.stream().filter(s->s.getAttribute("value").equalsIgnoreCase(sex)).peek(p->print("Clicking " + p.getAttribute("value"))).forEach(e->click(e));
        rdbtnGroupAge.stream().filter(s->s.getAttribute("value").equalsIgnoreCase(age)).peek(p->print("Clicking " + p.getAttribute("value"))).forEach(e->click(e));
    }

    public String getSelectedSex(){
        return getSelectedRadioBtn(rdbtnGroupSex);
    }

    public String getSelectedAge(){
        return getSelectedRadioBtn(rdbtnGroupAge);
    }


    public String getGroupValue(){
        click(btnGroupValue);
        waitForWebElementToAppear(txtGroup);
        return txtGroup.getText();
    }

    public String getCheckedValue(){
        click(btnCheckedValue);
        waitForWebElementToAppear(txtChecked);
        String checked = txtChecked.getText();
        print("Prompt : " + checked);
        return checked;
    }

}

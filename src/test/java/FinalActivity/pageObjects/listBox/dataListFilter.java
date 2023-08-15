package FinalActivity.pageObjects.listBox;

import FinalActivity.resources.abstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class dataListFilter extends abstractComponents {
    WebDriver driver;

    public dataListFilter(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void goTo(){
        driver.get("https://demo.seleniumeasy.com/data-list-filter-demo.html");
    }

    @FindBy (css = ".items")
    List<WebElement> results;
    @FindBy (xpath = "//div[@class='info-block block-info clearfix']/*")
    List<WebElement> infoBlockText;

    @FindBy (id = "input-search")
    WebElement txtbxSearch;

    public void search(String input){
        txtbxSearch.clear();
        print("Searching : " + input);
        txtbxSearch.sendKeys(input);
        Assert.assertEquals(txtbxSearch.getAttribute("value"),input);
        checkResults(input);
    }

    public void checkResults(String input){
        for(WebElement e : infoBlockText.stream().filter(s->!s.getText().isBlank()).collect(Collectors.toList())) {
            if (e.getText().contains(input)) {
                print("Match -> " + e.getText());
            }
        }
        print(results.stream().filter(s -> s.getAttribute("style").contains("block")).count() + " result/s found");
        divider();
    }
}

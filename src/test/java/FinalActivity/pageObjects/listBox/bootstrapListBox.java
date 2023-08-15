package FinalActivity.pageObjects.listBox;

import FinalActivity.resources.abstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class bootstrapListBox extends abstractComponents {
    WebDriver driver;

    public bootstrapListBox(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void goTo(){
        driver.get("https://demo.seleniumeasy.com/bootstrap-dual-list-box-demo.html");
    }

    @FindBy (css = ".dual-list.list-left.col-md-5>div>ul>li")
    List<WebElement> listbxLeft;

    @FindBy (css = ".dual-list.list-right.col-md-5>div>ul>li")
    List<WebElement> listbxRight;

    @FindBy (css = ".move-right")
    WebElement btnMoveRight;

    @FindBy (css = ".move-left")
    WebElement btnMoveLeft;

    @FindBy (xpath = "(//input[@name='SearchDualList'])[1]")
    WebElement txtbxLeft;

    @FindBy (xpath = "(//input[@name='SearchDualList'])[2]")
    WebElement txtbxRight;

    @FindBy (css = ".active")
    List<WebElement> activeSelection;

    @FindBy (xpath = "(//a[@title='select all'])[1]")
    WebElement btnSelectAllLeft;

    @FindBy (xpath = "(//a[@title='select all'])[2]")
    WebElement btnSelectAllRight;

    public void selectLeftListBxData(String[] data) {
        print("------Left Box-----");
        List<String> moveItems = selectData(data, listbxLeft, txtbxLeft, btnSelectAllLeft);
        print("Clicking Right move button");
        moveItems(listbxLeft,moveItems,btnMoveRight);
    }
    public void selectRightListBxData(String[] data){
        print("------Right Box-----");
        List<String> moveItems = selectData(data, listbxRight,txtbxRight, btnSelectAllRight);
        print("Clicking Left move button");
        moveItems(listbxLeft,moveItems,btnMoveLeft);
    }
    public List<String> selectData(String[] data, List<WebElement> listbox, WebElement textbox, WebElement button){
        List<String> movedItems = new ArrayList<>();
        if (!data[0].equalsIgnoreCase("All")){
            for (String datum : data) {
                textbox.sendKeys(datum);
                // Assert.assertTrue(textbox.getText().equalsIgnoreCase(datum));
                for(int i = 0; i<listbox.size(); i++){
                    if(listbox.get(i).getText().contains(datum)){
                        print("Selecting : " + datum);
                        click(listbox.get(i));
                      //  Assert.assertTrue(listbox.get(i).getText().contains(datum));
                        movedItems.add(listbox.get(i).getText());
                    }
                }
                textbox.clear();
                textbox.sendKeys(Keys.ARROW_LEFT);
            }
        } else{
            print("Clicking Select tick box");
            click(button);
            print("All items selected");
            listbox.forEach(e->movedItems.add(e.getText()));
        //    Assert.assertEquals(listbox.size(),activeSelection.size());
        }
        print("Selection count : " + activeSelection.size());
       return movedItems;
    }

    public void moveItems(List<WebElement> listbox, List<String> movedItems, WebElement button){
        click(button);
        boolean flag = true;
        if (!listbox.isEmpty()) {
            for (String movedItem : movedItems) {
                for (WebElement e : listbox.stream().filter(s->s.getAttribute("class").contains("active")).collect(Collectors.toList())) {
                    if (e.getText().equalsIgnoreCase(movedItem)) {
                        flag = true;
                    } else flag = false;
                }
            }
        }
        print("Items moved : " + flag);
        clearSelection();
    }



    public void clearSelection(){
        print("Clearing current active selections");
        for (WebElement e : activeSelection){
            click(e);
            print("Clearing : " + e.getText());
        }
        print("Selection is all cleared.");
  //      Assert.assertTrue(activeSelection.isEmpty());
    }


}

package FinalActivity.pageObjects.table;

import FinalActivity.resources.abstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class tableSortSearch extends abstractComponents {
    WebDriver driver;


    public tableSortSearch(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void goTo(){
        driver.get("https://demo.seleniumeasy.com/table-sort-search-demo.html");
    }

    @FindBy (xpath = "//tr/th")
    List<WebElement> tblHeaders;
    @FindBy (id = "example_next")
    WebElement btnNext;
    @FindBy (xpath = "//a[contains(text(),'1')]")
    WebElement btnFirstPage;
    @FindBy (xpath = "//select")
    WebElement drpdnEntries;
    @FindBy (xpath = "//input")
    WebElement txtbxSearch;
    @FindBy (xpath = "//tr/td")
    List<WebElement> allRows;

    public boolean filterListBy(String header){
        List<String> headerNames = tblHeaders.stream().map(s->s.getText()).collect(Collectors.toList());
        int colNum = headerNames.indexOf(header);

        Select se = new Select(drpdnEntries);
        se.selectByVisibleText("50");

        print("Sorting table by " + header);

        List<String> origList = getList(colNum);
        print("Unsorted List of values has been created");
        se.selectByVisibleText("10");
        if (tblHeaders.get(colNum).getAttribute("aria-sort") == null) {
            click(tblHeaders.get(colNum));
        }
        print("Original Sorted List of values has been created");
        List<String> sortedList = getList(colNum);
        return sortList(origList).equals(sortedList);
    }

    public List<String> getList(int colNum){
        List<String> colValues;
        ArrayList<String> colList = new ArrayList<>();
        colNum += 1;
        do {
            List<WebElement> rows = driver.findElements(By.xpath("//tr/td["+colNum+"]"));
            colValues = rows.stream().map(s -> s.getText()).collect(Collectors.toList());
            colValues.forEach(a->colList.add(a));

            if(colValues.size() == 10) {
                btnNext.click();
            }
        } while (colList.size() < 32);
        btnFirstPage.click();
        return colList;
    }

    public List<String> sortList(List<String> origList){
        List<String> sortedList = origList.stream().sorted().collect(Collectors.toList());
        print("Unsorted List has now been sorted");
        return sortedList;
    }

    public boolean search(String keyword){
        txtbxSearch.clear();
        txtbxSearch.sendKeys(keyword);
        print("Searching for : " + keyword);

        if(keyword.charAt(1)==' '){
            keyword = keyword.substring(0,1) + ". " + keyword.substring(2);
        }
        String finalKeyword = keyword;

        List<WebElement> colValues = allRows.stream().filter(s->s.getText().contains(finalKeyword)).collect(Collectors.toList());
        colValues.forEach(s-> print("Match -> " + s.getText()));
        print("Validating results... " + colValues.size() + " records found");
        divider();
        return colValues.size()!=0;
    }
}

package FinalActivity.pageObjects.datePickers;

import FinalActivity.resources.abstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class bootstrapDatePicker extends abstractComponents {
    WebDriver driver;
    String dateToday = String.valueOf(LocalDate.now().getDayOfMonth());
    Boolean flag;

    public bootstrapDatePicker(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void goTo(){
        driver.get("https://demo.seleniumeasy.com/bootstrap-date-picker-demo.html");
    }

    @FindBy (css="#sandbox-container1>div") WebElement dtpckrDateExample;
    @FindBy (xpath = "//input[@placeholder='Start date']") WebElement dtpckrStartDate;
    @FindBy (xpath = "//input[@placeholder='End date']") WebElement dtpckrEndDate;
    @FindBy (css = "div.datepicker-days>table>tfoot>tr>th.today") WebElement btnDateExToday;
    @FindBy (css = "div.datepicker-days>table>tfoot>tr>th.clear") WebElement btnDateExClear;
    @FindBy (xpath = "//input[@placeholder='dd/mm/yyyy']") WebElement txtDateExample;
    @FindBy (css = "th.dow") List<WebElement> txtDaysOfWeek;
    @FindBy (css = "td.day") List<WebElement> txtDates;

    public void openDatePicker(){
        click(dtpckrDateExample);
        print("Clicked Select Date");
    }
    public void openStartDatePicker(){
        click(dtpckrStartDate);
        print("Clicked Start Date");
    }

    public void openEndDatePicker(){
        click(dtpckrEndDate);
        print("Clicked End Date");
    }
    public String dateExClickToday(){
        click(btnDateExToday);
        print("Clicked Today button");
        print(txtDateExample.getAttribute("value") + " is selected.");
        return txtDateExample.getAttribute("value");
    }

    public String dateExClickClear() {
        openDatePicker();
        print("Current date value : '" + txtDateExample.getAttribute("value") + "'");
        click(btnDateExClear);
        print("Clicked Clear button");
        print("Date picker value became : '" + txtDateExample.getAttribute("value") + "'");
        return txtDateExample.getAttribute("value");
    }

    public void clickDate(String date){
        click(txtDates.get(getIndexDatesOfMonth(date)));
        print("Clicking date : " + date);
    }

    public int checkWeekStart(String day){
        int index = getWeekdayIndex(day);
        print("Weekstart should have index 0");
        print("Current index of " + day + " is " + index);
        return index;
    }

    public boolean checkDisabledSundayDates(){

        int index = getWeekdayIndex("Su")+1;
        List<WebElement> txtSundayDates = driver.findElements(By.xpath("//tr/td[" + index + "]"));
        flag = txtSundayDates.stream().anyMatch(s-> s.getAttribute("class").contains("disabled"));
        print("Checking if Sunday dates are disabled. Sunday is day #" + index);
        print("Sunday dates are disabled : " + flag);
        return flag;
    }

    public Boolean checkDisabledFutureDates(){
        flag = true;

        for (int i = getTodayIndex()+1; i < txtDates.size(); i++){
            if (flag == true){
             flag = txtDates.get(i).getAttribute("class").contains("disabled");
            }
        }
        print("Checking disabled dates. Dates after : " + dateToday);
        print("Dates disabled : " + flag);
        return flag;
    }

    public Integer getIndexDatesOfMonth(String day){
        int index = getDateIndex("1");
        int numDay = Integer.parseInt(day);
        ArrayList<Integer> indexDaysofMonth = new ArrayList<>();

        for (int i = 1; i <= 31; i++){
            indexDaysofMonth.add(index);
            index++;
        }
        return indexDaysofMonth.get(numDay-1);
    }

    public int getWeekdayIndex(String day){
        List<String> days = txtDaysOfWeek.stream().map(s->s.getText()).collect(Collectors.toList());
        return days.indexOf(day.substring(0,2));
    }

    public int getTodayIndex(){
        List<String> days = txtDates.stream().map(s->s.getText()).collect(Collectors.toList());
        return days.indexOf(String.valueOf(LocalDate.now().getDayOfMonth()));
    }

    public int getDateIndex(String date){
        List<String> days = txtDates.stream().map(s->s.getText()).collect(Collectors.toList());
        return days.indexOf(date);
    }

    public void setStartDate(String date){
        clickDate(date);
        print("Start date : " + dtpckrStartDate.getAttribute("value"));
    }

    public void setEndDate(String date){
        clickDate(date);
        print("End date : " + dtpckrEndDate.getAttribute("value"));

    }

    public String getStartDate(){
        return dtpckrStartDate.getAttribute("value");
    }

    public String getEndDate(){
        return dtpckrEndDate.getAttribute("value");
    }


}

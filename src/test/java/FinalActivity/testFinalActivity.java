package FinalActivity;

import FinalActivity.pageObjects.alertsModals.bootstrapAlerts;
import FinalActivity.pageObjects.alertsModals.bootstrapModals;
import FinalActivity.pageObjects.alertsModals.windowPopupModal;
import FinalActivity.pageObjects.datePickers.bootstrapDatePicker;
import FinalActivity.pageObjects.inputForms.*;
import FinalActivity.pageObjects.listBox.bootstrapListBox;
import FinalActivity.pageObjects.listBox.dataListFilter;
import FinalActivity.pageObjects.progressBarsSliders.bootstrapProgressBar;
import FinalActivity.pageObjects.table.tableSortSearch;
import FinalActivity.testComponents.BaseTest;
import FinalActivity.resources.Out;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Date;

public class testFinalActivity extends BaseTest {
    Boolean flag;

    @Test(dataProvider = "hashMapData", groups = {"InputForms"})
    public void simpleForm(HashMap<String,String> input) throws IOException {
        simpleFormDemo simpleForm = gotoSimpleFormDemo();

        Out.print("Verify message displayed is equal to input field");
        simpleForm.singleInputField(input.get("message"));
        Assert.assertEquals(input.get("message"),simpleForm.getMessage());
        Out.print("Message displayed is equal to input verified");

        Out.print("");
        Out.print("Verify if correct sum of a + b is displayed");
        String total = simpleForm.twoInputField(input.get("a"),input.get("b"));
        Assert.assertEquals(total,simpleForm.getTotal());
        Out.print("Correct sum displayed verified");

    }

    @Test(groups = {"InputForms"})
    public void checkBox() throws IOException {
        checkBoxDemo checkBox = gotoCheckBoxDemo();

        Out.print("Verify if clicking 'Check All' button will tick all options");
        checkBox.clickCheckAllButton();
        Assert.assertTrue(checkBox.getCheckboxStatus());
        Out.print("All options ticked verified");

        Out.print("");
        Out.print("Verify if button changed to 'Uncheck All'");
        Assert.assertEquals(checkBox.getButtonStatus(),"Uncheck All");
        Out.print("Button changed to Uncheck All verified");

        Out.print("");
        Out.print("Verify if unchecking one box will change the button value to 'Check All'");
        checkBox.clickOption();
        Assert.assertFalse(checkBox.getCheckboxStatus());
        Assert.assertEquals(checkBox.getButtonStatus(), "Check All");
        Out.print("Button changed to Check All verified");
    }

    @Test(dataProvider = "jsonData", groups = {"InputForms"})
    public void radioButton(HashMap<String,String> input) throws IOException {
        radioButtonsDemo radioButton = gotoRadioButtonsDemo();

        Out.print("Scenario: Clicking button will get selected value");
        Out.divider();

        radioButton.selectRadioBtnOption(input.get("rbOption"));

        Out.print("Verify if correct radio button is selected");
        Assert.assertEquals(input.get("rbOption"),radioButton.getRadioBtnOption());
        Out.print("Correct radio button selected verified");

        Out.print("");
        Out.print("Verify if text displays correct checked value");
        Assert.assertTrue(radioButton.getCheckedValue().contains(input.get("rbOption")));
        Out.print("Correct checked value displayed verified");

        Out.print("");
        Out.print("Scenario: Clicking button will get selected values from Sex and Age groups");
        Out.divider();
        radioButton.selectGrpRadioBtnOption(input.get("rbgrpSex"),input.get("rbgrpAge"));

        Out.print("");
        Out.print("Verify if correct radio buttons are selected");
        Assert.assertEquals(input.get("rbgrpSex"),radioButton.getSelectedSex());
        Out.print("Correct Sex is selected verified");
        Assert.assertEquals(input.get("rbgrpAge"),radioButton.getSelectedAge());
        Out.print("Correct Age Group is selected verified");

        Out.print("");
        Out.print("Verify if text displays correct selected value");
        Out.print("");
        Out.print(radioButton.getGroupValue());
        Out.print("");
        Assert.assertTrue(radioButton.getGroupValue().contains(input.get("rbgrpSex")));
        Out.print("Correct Sex Selected verified");
        Assert.assertTrue(radioButton.getGroupValue().contains(input.get("rbgrpAge")));
        Out.print("Correct Age group Selected verified");
    }

    @Test(dataProvider = "jsonData", groups = {"InputForms"})
    public void dropdownList(HashMap<String,String> input) throws IOException {
        selectDropdownList dropdownList = gotoSelectDropdownList();

        dropdownList.selectDay(input.get("day"));
        Assert.assertTrue(dropdownList.getSelectedDay().contains(input.get("day")));

        Out.print("");
        String[] states = {"California","Ohio","Texas"};
        dropdownList.selectMultiStates(states);

        dropdownList.clickFirstSelectedButton();
        Assert.assertTrue(dropdownList.getSelectedText().contains("First selected option"));

        dropdownList.clickGetAllSelected();
        Assert.assertTrue(dropdownList.getSelectedText().contains("Options selected"));
    }

    @Test(dataProvider = "inputFormData", groups = {"InputForms"})
    public void inputForm(String firstName, String lastName, String email, String phoneNum, String address, String city, String state, String zipCode, String webDomain, String hosting, String projDesc) throws IOException {
        inputFormDemo inputForm = gotoInputFormDemo();
        Out.print("Input Form Data");
        inputForm.typeData(firstName,lastName,email,phoneNum,address,city,zipCode,webDomain,projDesc);
        inputForm.selectState(state);
        inputForm.selectHosting(hosting);
        inputForm.submitForm();
    }

    @Test(dataProvider = "jsonData", groups = {"InputForms"})
    public void ajaxForm(HashMap<String,String> input) throws IOException {
        ajaxFormSubmit ajaxForm = gotoAjaxFormSubmit();
        Out.print("Ajax Form Sumit");
        ajaxForm.setData(input.get("name"),input.get("comment"));
        Assert.assertTrue(ajaxForm.submitForm().contains("Form submited Successfully!"));
    }

    @Test(dataProvider = "jQuery", groups = {"InputForms"})
    public void jQuerySelect(String country, String[] states, String territory, String file) throws IOException, InterruptedException {
        jQuerySelectDropdown jQuerySelect = gotoJQuerySelectDropdown();
        jQuerySelect.searchCountry(country);
        jQuerySelect.selectStates(states);
        jQuerySelect.selectTerritory(territory);
        jQuerySelect.selectFile(file);
    }

    @Test(groups = {"DatePickers"})
    public void datePicker() throws IOException, ParseException {
        bootstrapDatePicker datePicker = gotoBootstrapDatePicker();

        //  ---------------- DATE EXAMPLE --------------------

        Out.print("-------Date Example-------");
        datePicker.openDatePicker();
        /*** Future Dates disabled ***/
        Assert.assertTrue(datePicker.checkDisabledFutureDates());

        /*** Sunday Dates disabled ***/
        Assert.assertTrue(datePicker.checkDisabledSundayDates());

        /*** Week starts from Monday ***/
        Assert.assertTrue(datePicker.checkWeekStart("Monday")==0);
       // Assert.assertTrue(datePicker.checkWeekStart("Tuesday")==1);

        /*** Current Date ***/
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Assert.assertEquals(datePicker.dateExClickToday(),formatter.format(date));

        /*** Clear the date entered ***/
        Assert.assertEquals(datePicker.dateExClickClear(),"");

        //  ---------------- DATE RANGE EXAMPLE --------------------

        Out.print("");
        Out.print("-------Date Range Example-------");
        /*** Start Date ***/
        datePicker.openStartDatePicker();

        Assert.assertTrue(datePicker.checkDisabledSundayDates());
        Assert.assertTrue(datePicker.checkWeekStart("Sunday")==0);
        datePicker.setStartDate("25");
        Out.print("End date: " + datePicker.getEndDate());
        Out.print("Start date and end date should have the same value.");
        Assert.assertEquals(datePicker.getStartDate(),datePicker.getEndDate());

        /*** End Date ***/
        Out.print("");
        datePicker.openEndDatePicker();
        Assert.assertTrue(datePicker.checkDisabledSundayDates());
        Assert.assertTrue(datePicker.checkWeekStart("Sunday")==0);
        datePicker.setEndDate("29");

        /*** Compare 2 dates ***/
        Out.print("");

        Out.print("Start date: " + datePicker.getStartDate());
        Out.print("End date: " + datePicker.getEndDate());

        Date startDate = formatter.parse(datePicker.getStartDate());
        Date endDate = formatter.parse(datePicker.getEndDate());

        flag = startDate.compareTo(endDate) < 0;
        Out.print("Start date should be less than end date. Validation is : " + flag);
        Assert.assertTrue(flag);

    }

    @Test(groups = {"Table"})
    public void tableSortSearch() throws IOException {
        tableSortSearch table = gotoTableSortSearch();

        flag = table.filterListBy("Position");
        Out.print("List is sorted : " + flag);
        Out.print("");

        Assert.assertTrue(table.search("Developer"));
        Assert.assertTrue(table.search("San Francisco"));
        Assert.assertTrue(table.search("Mon"));
        Assert.assertTrue(table.search("725"));
        Assert.assertTrue(table.search("Y Berry"));
        Assert.assertTrue(table.search("23"));
        Assert.assertFalse(table.search("Pepper"));
    }

    @Test(groups = {"ProgressBarsSliders"})
    public void progressBar() throws IOException {
        bootstrapProgressBar progressBar = gotoBootstrapProgressBar();

        progressBar.clickDownloadButton();
        progressBar.waitForDownload();
    }

    @Test(groups = {"AlertsModals"})
    public void alerts() throws IOException, InterruptedException {
        bootstrapAlerts alerts = gotoBootstrapAlerts();

        alerts.clickButtons();
    }

    @Test(groups = {"AlertsModals"})
    public void modals() throws IOException, InterruptedException {
        bootstrapModals modals = gotoBootstrapModals();

        Out.print("-------Single Modal Example-------");
        modals.launchSingleModal();

        Out.print("");
        Out.print("-------Multiple Modal Example-------");
        modals.launchMultipleModal();
    }

    @Test(groups = {"AlertsModals"})
    public void popupModals() throws IOException {
        windowPopupModal popupModal = gotoWindowPopupModal();
        popupModal.clickButtons();
    }

    @Test(dataProvider = "DualList", groups = {"AlertsModals"})
    public void listBox(String[] leftListBox, String[] rightListBox) throws IOException {
        bootstrapListBox listBox = gotoBootstrapListBox();
        listBox.selectLeftListBxData(leftListBox);
        listBox.selectRightListBxData(rightListBox);
    }

    @Test(groups = {"AlertsModals"})
    public void dataList() throws IOException {
        dataListFilter dataList = gotoDataListFilter();

        dataList.search("Brian");
        dataList.search("shizzle");
        dataList.search("234");
        dataList.search("test3@company.com");
        dataList.search("Meii");
        dataList.search("Manager");
    }
}

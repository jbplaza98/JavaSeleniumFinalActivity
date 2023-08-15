package FinalActivity.testComponents;

import FinalActivity.pageObjects.alertsModals.*;
import FinalActivity.pageObjects.datePickers.bootstrapDatePicker;
import FinalActivity.pageObjects.inputForms.*;
import FinalActivity.data.dataProviders;
import FinalActivity.pageObjects.listBox.*;
import FinalActivity.pageObjects.progressBarsSliders.bootstrapProgressBar;
import FinalActivity.pageObjects.table.tableSortSearch;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.AfterMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest extends dataProviders {
    public WebDriver driver;
    public simpleFormDemo simpleForm;
    public checkBoxDemo checkBox;
    public radioButtonsDemo radioButton;
    public selectDropdownList dropdownList;
    public inputFormDemo inputForm;
    public ajaxFormSubmit ajaxForm;
    public jQuerySelectDropdown jQuerySelect;
    public bootstrapDatePicker datePicker;
    public tableSortSearch table;
    public bootstrapProgressBar progressBar;
    public bootstrapAlerts alerts;
    public bootstrapModals modals;
    public windowPopupModal popupModal;
    public bootstrapListBox listBox;
    public dataListFilter dataList;
    public WebDriver initializeDriver() throws IOException {
        ChromeOptions capability = new ChromeOptions();
       // capability.setCapability(CapabilityType., true);
        capability.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+
                "/src/test/java/FinalActivity/resources/GlobalData.properties");
        prop.load(fis);
        String browserName = prop.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver(capability);
        } else if (browserName.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        return driver;
    }
    public simpleFormDemo gotoSimpleFormDemo() throws IOException{
        driver = initializeDriver();
        simpleForm = new simpleFormDemo(driver);
        simpleForm.goTo();
        return simpleForm;
    }
    public checkBoxDemo gotoCheckBoxDemo() throws IOException{
        driver = initializeDriver();
        checkBox = new checkBoxDemo(driver);
        checkBox.goTo();
        return checkBox;
    }
    public radioButtonsDemo gotoRadioButtonsDemo() throws IOException{
        driver = initializeDriver();
        radioButton = new radioButtonsDemo(driver);
        radioButton.goTo();
        return radioButton;
    }
    public selectDropdownList gotoSelectDropdownList() throws IOException{
        driver = initializeDriver();
        dropdownList = new selectDropdownList(driver);
        dropdownList.goTo();
        return dropdownList;
    }
    public inputFormDemo gotoInputFormDemo() throws IOException{
        driver = initializeDriver();
        inputForm = new inputFormDemo(driver);
        inputForm.goTo();
        return inputForm;
    }
    public ajaxFormSubmit gotoAjaxFormSubmit() throws IOException{
        driver = initializeDriver();
        ajaxForm = new ajaxFormSubmit(driver);
        ajaxForm.goTo();
        return ajaxForm;
    }
    public jQuerySelectDropdown gotoJQuerySelectDropdown() throws IOException{
        driver = initializeDriver();
        jQuerySelect = new jQuerySelectDropdown(driver);
        jQuerySelect.goTo();
        return jQuerySelect;
    }
    public bootstrapDatePicker gotoBootstrapDatePicker() throws IOException{
        driver = initializeDriver();
        datePicker = new bootstrapDatePicker(driver);
        datePicker.goTo();
        return datePicker;
    }
    public tableSortSearch gotoTableSortSearch() throws IOException{
        driver = initializeDriver();
        table = new tableSortSearch(driver);
        table.goTo();
        return table;
    }
    public bootstrapProgressBar gotoBootstrapProgressBar() throws IOException{
        driver = initializeDriver();
        progressBar = new bootstrapProgressBar(driver);
        progressBar.goTo();
        return progressBar;
    }
    public bootstrapAlerts gotoBootstrapAlerts() throws IOException{
        driver = initializeDriver();
        alerts = new bootstrapAlerts(driver);
        alerts.goTo();
        return alerts;
    }
    public bootstrapModals gotoBootstrapModals() throws IOException{
        driver = initializeDriver();
        modals = new bootstrapModals(driver);
        modals.goTo();
        return modals;
    }
    public windowPopupModal gotoWindowPopupModal() throws IOException{
        driver = initializeDriver();
        popupModal = new windowPopupModal(driver);
        popupModal.goTo();
        return popupModal;
    }
    public bootstrapListBox gotoBootstrapListBox() throws IOException{
        driver = initializeDriver();
        listBox = new bootstrapListBox(driver);
        listBox.goTo();
        return listBox;
    }
    public dataListFilter gotoDataListFilter() throws IOException{
        driver = initializeDriver();
        dataList = new dataListFilter(driver);
        dataList.goTo();
        return dataList;
    }

//    @AfterMethod(alwaysRun = true)
//    public void tearDown(){
//    driver.close();
//    }


}

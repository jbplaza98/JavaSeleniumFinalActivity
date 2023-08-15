package FinalActivity.pageObjects.progressBarsSliders;

import FinalActivity.resources.abstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class bootstrapProgressBar extends abstractComponents {
    WebDriver driver;


    public bootstrapProgressBar(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void goTo(){
        driver.get("https://demo.seleniumeasy.com/bootstrap-download-progress-demo.html");
    }

    @FindBy (id = "cricle-btn")
    WebElement btnDownload;

    @FindBy (css = ".percenttext")
    WebElement txtPercentage;

    @FindBy (xpath = "//div[contains(text(),'100%')]")
    WebElement txt100;

    public void clickDownloadButton(){
        click(btnDownload);
        print("Clicked download button");
    }

    public void waitForDownload(){
         waitForProgressBarToFinish();
         waitForWebElementToAppear(txt100);
    }

    public void waitForProgressBarToFinish(){
        String percentage = txtPercentage.getText();
        if(!percentage.equalsIgnoreCase("100%")){
            waitForProgressBarToFinish();
        }else {
            print("Download is completed");
        }

    }


}

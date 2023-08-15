package FinalActivity.pageObjects.alertsModals;

import FinalActivity.resources.abstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class bootstrapModals extends abstractComponents {
    WebDriver driver;

    public bootstrapModals(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void goTo(){
        driver.get("https://demo.seleniumeasy.com/bootstrap-modal-demo.html");
    }

    @FindBy (xpath = "(//div[@class='panel panel-default'])[2]/div[2]/a")
    WebElement btnSingleModal;
    @FindBy (xpath = "(//div[@class='panel panel-default'])[3]/div[2]/a")
    WebElement btnMultipleModal;
    @FindBy (id = "myModal0")
    WebElement singleModal;
    @FindBy (id ="myModal")
    WebElement firstModal;
    @FindBy (id ="myModal2")
    WebElement secondModal;

    public void launchSingleModal() throws InterruptedException {
        click(btnSingleModal);
        print("Launch Modal button is clicked");
        waitForWebElementToAppear(singleModal);
        print("Modal title: " + getModalTitle(singleModal));
        print("Modal says : " + getModalText(singleModal));
        closeModal(singleModal);
    }

    public String getModalText(WebElement e){
        return e.findElement(By.xpath("//div[@class='modal-body']")).getText();
    }

    public String getModalTitle(WebElement e){
        return e.findElement(By.xpath("//div/div/div[@class='modal-header']/h4")).getText();
    }

    public void closeModal(WebElement e) throws InterruptedException {
        print("Clicking close button");
        click(e.findElement(By.xpath("//div/div/div[@class='modal-footer']/a[contains(text(),'Close')]")));
        print("Modal is closed");
    }

    public void launchMultipleModal(){
        click(btnMultipleModal);
        print("Launch Modal button is clicked");
        waitForWebElementToAppear(firstModal);
        print("Modal title : " + driver.findElement(By.xpath("//div[@id='myModal']/div/div/div[@class='modal-header']/h4")).getText());
//        print("Why doesn't this work : " + firstModal.findElement(By.xpath("//div/div/div[@class='modal-header']/h4")).getText());
//        print("This also not working: " + getModalTitle(firstModal));
        print("Modal says : " + driver.findElement(By.xpath("//div[@id='myModal']/div/div/div[@class='modal-body']")).getText());

        print("Clicking Launch Modal button");
        firstModal.findElement(By.xpath("//div/div/div[@class='modal-body']/a")).click();
        waitForWebElementToAppear(secondModal);
        print("Modal title : " + driver.findElement(By.xpath("//div[@id='myModal2']/div/div/div[@class='modal-header']/h4")).getText());
        print("Modal says : " + driver.findElement(By.xpath("//div[@id='myModal2']/div/div/div[@class='modal-body']")).getText());
        click(driver.findElement(By.xpath("//div[@id='myModal2']/div/div/div[@class='modal-footer']/a[contains(text(),'Save changes')]")));
        print("Clicked save changes");
    }


}

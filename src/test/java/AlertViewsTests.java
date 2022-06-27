import org.openqa.selenium.WebElement;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * AlertViewsTests
 */
public class AlertViewsTests extends IOSUITests {
    public static void main(String[] args) {
        createApp();
        if (app.launch()) {
            IOSDriver<IOSElement> driver = app.driver;
            WebElement alertViews = driver.findElementByAccessibilityId("Alert Views");
            alertViews.click();
            WebElement okCancel = driver.findElementByXPath("//XCUIElementTypeStaticText[@name='Okay / Cancel']");
            okCancel.click();
            driver.findElementById("OK").click();
            app.goBack("UIKitCatalog");
        }
    }
}
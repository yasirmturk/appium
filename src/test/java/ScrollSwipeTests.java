import io.appium.java_client.ios.IOSElement;

/*
 * ScrollSwipeTests
 */
public class ScrollSwipeTests extends IOSUITests {
    public static void main(String[] args) {
        createApp();
        if (app.launch()) {
            testScroll();
            testSlider();
        }
    }

    public static void testScroll() {
        app.findElement("Web View", true).click();
        app.waitForIOSAnimations();
        app.goBack("UIKitCatalog");
        app.findElement("Picker View").click();
        app.findElement("Red color component value").sendKeys("80");
        app.findElement("Green color component value").sendKeys("220");
        app.findElement("Blue color component value").sendKeys("105");
        String blueValue = app.findElement("Blue color component value").getText();
        System.out.println(blueValue);
        app.goBack("UIKitCatalog");
    }

    public static void testSlider() {
        app.findElement("Sliders").click();
        app.waitForIOSAnimations();
        IOSElement slider = app.driver.findElementByXPath("//XCUIElementTypeSlider");
        slider.setValue("0");
        app.waitForIOSAnimations();
        slider.setValue("1");
        app.waitForIOSAnimations();
        app.goBack("UIKitCatalog");
    }
}

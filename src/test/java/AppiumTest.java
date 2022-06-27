import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumTest {
    static WebDriverWait wait;
    public static void main(String[] args) {
        try {
            AppiumDriver<MobileElement> driver = openIOSApp();
            wait = new WebDriverWait(driver, 10);
            System.out.println("Application started with driver: " + driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AppiumDriver<MobileElement> openIOSApp() throws MalformedURLException {
        // 1. Add the needed specs such as device name, device id, app activity we want to start
        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "IOS");
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15.5");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8");
        // cap.setCapability(MobileCapabilityType.UDID, "07FCA5AF-4E05-4C76-B3F0-73CBE4508F22");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        // cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
        cap.setCapability(MobileCapabilityType.APP, "/Users/yasir.turk/Projects/appium/builds-ios/UIKitCatalog.app");
        // More
        cap.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 5000);//50000
        cap.setCapability(IOSMobileCapabilityType.COMMAND_TIMEOUTS, 5000);//12000

        // 2. The iOS Simulator is on our laptop in this case
        URL url = new URL("http://0.0.0.0:4723/wd/hub");

        // 3. Add the Appium Driver that allows Appium to start the app
        return new IOSDriver<>(url, cap);
    }
}

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * IOSApp wraps the driver and capabilities setup
 */
public class IOSApp {
    public int animationDuration = 310;
    public IOSDriver<IOSElement> driver;
    private DesiredCapabilities capabilities;

    public IOSApp(String sdkVersion, String appPath) {
        capabilities = prepareCapabilities(sdkVersion, appPath);
    }

    public boolean launch() {
        URL url;
        try {
            url = appiumURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        }

        // 3. Add the Appium Driver that allows Appium to start the app
        driver = new IOSDriver<>(url, capabilities);

        System.out.println("Application started");
        return true;
    }

    public IOSElement findElement(String accessibilityId) {
        // waitForIOSAnimations();
        return driver.findElementByAccessibilityId(accessibilityId);
    }

    public IOSElement findElement(String accessibilityId, boolean shouldScroll) {
        if (shouldScroll) {
            scroll(false, accessibilityId);
        }
        return findElement(accessibilityId);
    }

    public void holdOn(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForIOSAnimations() {
        holdOn(animationDuration);
    }

    public void goBack(String name) {
        // back
        driver.findElementByXPath("//XCUIElementTypeButton[@name='" + name + "']").click();
    }

    public void scroll(boolean upOrDown, String accessibilityId) {
        HashMap<String, Object> scrollArgs = new HashMap<>();
        scrollArgs.put("direction", upOrDown ? "up" : "down");
        scrollArgs.put("accessibility id", accessibilityId);
        driver.executeScript("mobile:scroll", scrollArgs);
    }

    // Private methods

    private URL appiumURL() throws MalformedURLException {
        // 2. The iOS Simulator is on docker in this case
        URL url = new URL("http://host.docker.internal:4723/wd/hub");
        return url;
    }

    private DesiredCapabilities prepareCapabilities(String sdkVersion, String appPath) {
        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "IOS");
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, sdkVersion);
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8");
        // cap.setCapability(MobileCapabilityType.UDID,
        // "07FCA5AF-4E05-4C76-B3F0-73CBE4508F22");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        // cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
        cap.setCapability(MobileCapabilityType.APP, appPath);
        // More
        cap.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 5000);// 50000
        cap.setCapability(IOSMobileCapabilityType.COMMAND_TIMEOUTS, 5000);// 12000

        return cap;
    }
}
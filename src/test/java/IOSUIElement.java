import io.appium.java_client.ios.IOSElement;

public class IOSUIElement extends IOSElement {
    public String id;
    public IOSUIElement(String accessibilityId) {
        id = accessibilityId;
    }
}
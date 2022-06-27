public class IOSUITests {
    public static String sdkVersion = "14.4";
    public static String appPath = "/Users/yasir.turk/Projects/appium/builds-ios/UIKitCatalog.app";
    public static IOSApp app;

    public static void createApp() {
        app = new IOSApp(sdkVersion, appPath);
    }
}

package com.test.helpers;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class WebDriverHelper extends EventFiringWebDriver {
    private static final Logger LOG = LoggerFactory
            .getLogger(WebDriverHelper.class);
    private static RemoteWebDriver REAL_DRIVER = null;
    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            REAL_DRIVER.quit();
        }
    };
    private static String BROWSER;
    private static String PLATFORM;
    private static String DRIVER_PATH;
    private static String FILE_SEPARATOR;
    private static String SELENIUM_HOST;
    private static String SELENIUM_PORT;
    private static String SELENIUM_REMOTE_URL;
    private static Dimension BROWSER_WINDOW_SIZE;
    private static Integer BROWSER_WINDOW_WIDTH;
    private static Integer BROWSER_WINDOW_HEIGHT;
    private static final String DRIVER_ROOT = File.separator + "tools";

    static {
        Props.loadRunConfigProps("/environment.properties");
        SELENIUM_HOST = System.getProperty("driverhost");
        SELENIUM_PORT = System.getProperty("driverport");
        FILE_SEPARATOR = System.getProperty("file.separator");
        PLATFORM = Props.getProp("platform");
        BROWSER = Props.getProp("browser");
        BROWSER_WINDOW_WIDTH = Integer.parseInt(Props.getProp("browser.width"));
        BROWSER_WINDOW_HEIGHT = Integer.parseInt(Props.getProp("browser.height"));
        BROWSER_WINDOW_SIZE = new Dimension(BROWSER_WINDOW_WIDTH, BROWSER_WINDOW_HEIGHT);

        try {
            System.out.println("the driver is " + BROWSER.toLowerCase());
            switch (BROWSER.toLowerCase()) {
                case ("chrome"):
                    System.setProperty("webdriver.chrome.driver", getDriverPath());
                    startChromeDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Browser " + BROWSER + " or Platform "
                            + PLATFORM + " type not supported");

            }

        } catch (IllegalStateException e) {
            LOG.error(e.getMessage() + ". " + "FIX path for  " + DRIVER_ROOT
                    + " Browser parameter " + BROWSER + " Platform parameter " + PLATFORM
                    + " type not supported");
        }
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    private WebDriverHelper() {
        super(REAL_DRIVER);
    }

    public static WebDriver getWebDriver() {
        return REAL_DRIVER;
    }

    private static String getDriverPath() {

        String userDir, path = "";
        userDir = System.getProperty("user.dir");
        File file;
        try {
            if (BROWSER.equals("chrome") && PLATFORM.contains("mac")) {
                DRIVER_PATH = DRIVER_ROOT + FILE_SEPARATOR + "chromedriver"
                        + FILE_SEPARATOR + "mac" + FILE_SEPARATOR
                        + "chromedriver";
            } else if (BROWSER.equals("chrome") && PLATFORM.contains("win32")) {
                DRIVER_PATH = DRIVER_ROOT + FILE_SEPARATOR + "chromedriver"
                        + FILE_SEPARATOR + PLATFORM + FILE_SEPARATOR
                        + "chromedriver.exe";
            } else if (BROWSER.equals("chrome") && PLATFORM.contains("linux")) {
                DRIVER_PATH = "/opt/wsc/chrome_driver/chromedriver";
            }
            file = new File(userDir + DRIVER_PATH);
            path = file.getCanonicalPath();
        } catch (IOException e) {
            LOG.error(e.getMessage() + ". " + "There is a problem in finding/executing the driver " +
                    "at the path:" + userDir + path);
        }
        return path;
    }


    private static void startChromeDriver() {

        REAL_DRIVER = new ChromeDriver(
                ChromeDriverService.createDefaultService(), getChromeOptions());
        REAL_DRIVER.manage().window().setSize(BROWSER_WINDOW_SIZE);
    }


    private static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.SERVER, Level.OFF);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
        chromeOptions.addArguments("--disable-web-security");
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--test-type");
        chromeOptions.addArguments("disable-logging");
        chromeOptions.addArguments("log-level=3");
        chromeOptions.addArguments("silent");

        chromeOptions.merge(capabilities);
        return chromeOptions;
    }


    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException(
                    "You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        super.close();
    }
}

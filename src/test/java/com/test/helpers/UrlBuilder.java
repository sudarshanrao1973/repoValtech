package com.test.helpers;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(UrlBuilder.class);
    private static final String RUN_CONFIG_PROPERTIES = "/environment.properties";
    private static URL basePath;
    private static WebDriver webDriver;

    static {
        try {
            Props.loadRunConfigProps(RUN_CONFIG_PROPERTIES);
            basePath = new URL(Props.getProp("url"));

        } catch (MalformedURLException e) {
            LOG.error(e.getMessage());
        }

    }

    public static void startAtHomePage() {

        WebDriverHelper.getWebDriver().navigate().to((basePath));
    }

    public static String readValueFromConfig(String key) {
        return Props.getProp(key);
    }


}

package com.test.helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;


public abstract class PageObject {
    private static final long DRIVER_WAIT_TIME = 10;
    private static final Logger LOG = LoggerFactory.getLogger(PageObject.class);
    protected WebDriverWait wait;
    protected WebDriver webDriver;



    protected PageObject() {
        this.webDriver = WebDriverHelper.getWebDriver();
        if(webDriver == null) {
            System.out.println( "driver is null");
        }
        else{
            System.out.println("the driver is not null");
        }
        this.wait = new WebDriverWait(webDriver, DRIVER_WAIT_TIME);
    }

    /**
     * Find the dynamic element wait until its visible
     *
     * @param by Element location found by css, xpath, id etc...
     **/
    protected WebElement waitForExpectedElement(final By by) {
        return wait.until(visibilityOfElementLocated(by));
    }


}
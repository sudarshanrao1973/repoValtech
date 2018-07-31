package com.test.pageobjects;

import com.test.helpers.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import static org.openqa.selenium.By.cssSelector;


public class HomePage extends PageObject {

    private By acceptCookies = cssSelector("#CybotCookiebotDialogBodyButtonAccept");
    private By topMenu = cssSelector(".icon-menu");
    private By workLink = cssSelector(".site-nav__menu__primary>li:nth-child(1)>button");
    private By serviceLink = cssSelector(".site-nav__menu__primary>li:nth-child(2)>button");
    private By aboutLink = cssSelector(".site-nav__menu__primary>li:nth-child(5)>button");
    private By sectionHeading = cssSelector(".site-nav__main__content-box__title");
    private By officeLocation = cssSelector(".contact-item__office-description");


    public String getSectionHeading() {
      return   waitForExpectedElement(sectionHeading).getText();
    }


    public void clickOnWorkLink() {
        waitForExpectedElement(workLink).click();
    }


    public void clickOnServiceLink() {
        waitForExpectedElement(serviceLink).click();
    }

    public void clickOnAboutLink() {
        waitForExpectedElement(aboutLink).click();
    }

    public void clickOnTopMenu() {
        waitForExpectedElement(topMenu).click();
    }


    public boolean isCookiesMessageDisplayed() {
        return waitForExpectedElement(acceptCookies).isDisplayed();
    }

    public void clickOnAcceptCookies() {
        try {
            if (isCookiesMessageDisplayed() == true) {
                waitForExpectedElement(acceptCookies).click();
            }
        } catch (TimeoutException e) { // nothing to do}
        }
    }
}

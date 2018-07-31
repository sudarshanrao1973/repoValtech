package com.test;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "target/test-classes", tags = {"@web"}, monochrome = true, plugin = {
        "pretty", "html:target/cucumber-report",
        "json:target/cucumber-report/cucumber.json",
        "rerun:target/cucumber-report/rerun.txt"},
        glue = "com.test")
public class RunSuite extends AbstractTestNGCucumberTests {

  }

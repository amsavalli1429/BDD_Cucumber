package org_UI;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
        features = "src/test/resources/Features/page.feature",
        glue = {"org_UI"},
        //tags = "@smoke or @Regression",
        //tags = "@smoke and not @Regression",
        plugin = {
                "pretty",
                "html:src/test/resources/cucumberreport/cucumber-reports.html",
                "json:src/test/resources/cucumberreport/cucumber.json",
                "junit:src/test/resources/cucumberreport/cucumber.xml"
        },
        monochrome = true
)

public class TestRunners extends AbstractTestNGCucumberTests{

}




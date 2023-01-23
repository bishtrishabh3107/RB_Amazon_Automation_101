package org.amazon.runnerFiles;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.amazon.utils.BrowserConfig;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions
        (
                features= "src/test/resources/features/",
                glue= "org/amazon/stepDefinitions",
                tags = "@Regression",
                dryRun = false
        )
public class testRunnerFile {
        static BrowserConfig browserConfig = new BrowserConfig();

//        @BeforeClass
//        public static void setup() {
//                browserConfig.getDriver();
//        }

        @AfterClass
        public static void teardown(){
                browserConfig.closeDriver();
        }

}

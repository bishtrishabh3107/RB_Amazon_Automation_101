package org.amazon.runnerFiles;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.amazon.utils.BrowserConfig;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions
        (
                features= "src/test/resources/features/Login.feature",
                glue= "org/amazon/stepDefinitions"
        )
public class testRunnerFile {

        static BrowserConfig browserConfig=new BrowserConfig();

        @AfterClass
        public static void teardown(){
                browserConfig.closeDriver();
        }

}

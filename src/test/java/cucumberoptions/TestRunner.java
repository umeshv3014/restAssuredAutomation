package cucumberoptions;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/Features/placeValidation.feature", glue = {"stepdefinations"})
// path to feature file pkg and stepdefination pkg name
public class TestRunner {


}

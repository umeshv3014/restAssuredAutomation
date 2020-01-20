package cucumberoptions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/Features/placeValidation.feature", 
				 glue = "stepdefinations")
// path to feature file pkg and stepdefination pkg name
public class TestRunner {

}

package stepdefinations;

import org.junit.runner.RunWith;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
public class Login {

	@Given("^user is on home page login div opened$")
    public void user_is_on_home_page_login_div_opened() {
       System.out.println("given");
    }

	 @When("^user id and password provided and \"([^\"]*)\" clicked on login button \"([^\"]*)\"$")
	    public void user_id_and_password_provided_and_something_clicked_on_login_button_something(String strArg1, String strArg2) throws Throwable {
		 System.out.println(strArg1);
		 System.out.println(strArg2);
	 }

    @Then("^validate the loging$")
    public void validate_the_loging(){
       System.out.println("then");
    }

    @And("^card displayed \"([^\"]*)\"$")
    public void card_displayed_something(String strArg1){
        System.out.println(strArg1);
    }

}

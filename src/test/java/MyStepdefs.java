import com.auto.main.lesson7.*;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selenide.open;

public class MyStepdefs {
    /*
    Saucedemo steps
     */
    @Given("User Authorized")
    public void userAuthorized() {
        open("https://www.saucedemo.com/");
        new AuthSaucedemo().login("standard_user", "secret_sauce");
    }

    @When("click on product by name {string}")
    public void clickOnProductByNameName(String name) {
        new Products().clickOnProductByName(name);
    }

    @And("click add to cart")
    public void clickAddToCart() {
        new ProductItem().clickAddToCart();
    }

    @Then("check sum and remove button {string}")
    public void checkSumAndRemoveButtonSumma(String summa) {
        new ProductItem().checkSumAndRemoveButton(summa);
    }

    /*
    Wink steps
     */
    @Given("The Wink main page is open")
    public void theWinkMainPageIsOpen() {
        open("https://wink.ru/");
    }

    @When("Click on random element from films list")
    public void clickOnRandomElementFromFilmsList() {
        new WInkIndex().clickOnRandomElementFromList();
    }

    @Then("Check setup application button is displayed")
    public void checkSetupApplicationButtonIsDisplayed() {
        new WinkFilm().checkSetupAppButtonIsDisplayed();
    }


    @When("^Click on item from popup menu \"(.*)\"$")
    public void clickOnItemFromPopupMenuItemXpath(String itemXpath) {
        new WInkIndex().clickItem(itemXpath);
    }

    @Then("^Check header is displayed \"(.*)\"$")
    public void checkHeaderIsDisplayedHeaderXpath(String headerXpath) {
        new WInkIndex().checkHeader(headerXpath);
    }

    @After(value = "@close")
    public void quitBrowser() {
        Selenide.closeWebDriver();
    }

}

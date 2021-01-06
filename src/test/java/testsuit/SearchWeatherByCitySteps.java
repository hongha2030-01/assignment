package testsuit;

import com.fasterxml.jackson.core.JsonProcessingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import base.BaseSteps;
import framework.objects.responseobjects.SearchWeatherByCityResponse;
import framework.utils.api.Communication;
import framework.utils.data.DataConverter;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class SearchWeatherByCitySteps extends BaseSteps {

    private static final Double DELTA = 0.1;
    private static final String CALLBACK_QUERY_NAME = "callback";

    private RequestSpecification request;
    private String callback;
    private SearchWeatherByCityResponse searchWeatherByCityResponse;

    private Map<String, String> parameters = new HashMap<>();

    @Given("^I get weather in the city with the URI \"(.*)\"$")
    public void setupClient(String uri) {
        RestAssured.baseURI = StringUtils.join(getHostByEnvironment(), uri);
        request = RestAssured.given();
    }

    @When("^I put the parameter query \"(.*)\" with data (.*)$")
    public void addQueryParameter(String queryName, String queryValue) {
        if (!queryValue.isEmpty()) {
            parameters.put(queryName, queryValue);
        }

        if (queryName.equalsIgnoreCase(CALLBACK_QUERY_NAME) && !queryValue.isEmpty()) {
            callback = StringUtils.join(queryValue, "(");
        }
    }

    @When("^I send the request and expect that the status code should be (.*)$")
    public void sendRequest(int statusCode) throws JsonProcessingException {
        Response response = Communication.sendGetRequest(request, parameters);

        Assert.assertEquals(statusCode, response.getStatusCode());


        String responseJson = callback == null ? response.asPrettyString()
                : StringUtils.removeEnd(StringUtils.removeStart(response.asPrettyString(), callback), ")");

        searchWeatherByCityResponse = DataConverter.convertJsonToObject(responseJson, SearchWeatherByCityResponse.class);
    }


    @Then("^responseCity (.*), responseCountry (.*), geoCoords (.*) should be correct$")
    public void verifyResponse(String responseCity,String responseCountry, String geoCoords) {
        searchWeatherByCityResponse.getList().forEach(city -> {
            Assert.assertEquals(responseCity, DataConverter.removeAccents(city.getName()));
            Assert.assertEquals(responseCountry, city.getSys().getCountry());

            String[] expectedCoords = geoCoords.split(",");
            Assert.assertEquals(Double.parseDouble(expectedCoords[0].trim()), city.getCoord().getLat(), DELTA);
            Assert.assertEquals(Double.parseDouble(expectedCoords[0].trim()), city.getCoord().getLat(),DELTA);
        });
    }

    @Then("^no city is returned$")
    public void noCityIsReturned() {
        Assert.assertEquals(0, searchWeatherByCityResponse.getCount().longValue());
        Assert.assertEquals(0, searchWeatherByCityResponse.getList().size());
    }


    @When("^I put the parameter query \"(.*)\" with the text having length (.*)$")
    public void addQueryParameterWithLength(String queryName, int length) {
        parameters.put(queryName, RandomStringUtils.randomAlphabetic(length));
    }


}

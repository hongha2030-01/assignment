package framework.utils.api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class Communication {

    public static Response sendGetRequest(RequestSpecification request, Map<String, String> parameters) {
        request.header("Content-type", "application/json");
        request.queryParams(parameters);
        return request.get();
    }
}

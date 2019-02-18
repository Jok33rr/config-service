package com.smarthouse.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.get;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

class ConfigTestUtil {

    private static Gson gson = new Gson();

    static final String NAME = "name";
    static final String USER = "user";
    static final String DEFAULT = "default";
    static final String GATEWAY = "gateway";

    static void checkPropertiesAccessibility(String searchPath) {
        checkPropertiesAccessibility(searchPath, DEFAULT);
    }

    static void checkPropertiesAccessibility(String searchPath, String profile) {
        // Get request answer in JSON format
        JsonObject answer = request(String.format("/%s/%s", searchPath, profile));
        // Check whether config answer contains searchPath as name
        assertTrue(answer.has(NAME));
        assertEquals(answer.get(NAME).getAsString(), searchPath);
    }

    static JsonObject request(String address) {
        Response response = get(address); // Make a request
        return gson.fromJson(response.body().print(), JsonObject.class); // Convert answer to json
    }
}

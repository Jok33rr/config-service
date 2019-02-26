package com.smarthouse.config;

import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.smarthouse.config.ConfigTestUtil.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigServiceApplication.class)
@TestPropertySource(value = {"classpath:application.yml"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ConfigServiceApplicationTest {

    private static final String LOCALHOST = "http://localhost";
    @Value("${server.port}")
    private int port;

    @Before
    public void setUp() {
        RestAssured.baseURI = LOCALHOST;
        RestAssured.port = port;
    }

    @Test
    public void gatewayProperties() {
        checkPropertiesAccessibility(GATEWAY);
    }

    @Test
    public void userProperties() {
        checkPropertiesAccessibility(USER);
    }

}
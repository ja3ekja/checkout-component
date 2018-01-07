package com.pragmaticcoders.checkoutcomponent.integrationtest;

import com.pragmaticcoders.checkoutcomponent.CheckoutComponentApplication;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CheckoutComponentApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationBucketTests {

    @LocalServerPort
    private int port;

    @Autowired


    private TestRestTemplate restTemplate = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();
    private HttpEntity<String> entity = new HttpEntity<>(headers);

    @Test
    public void getTwoItemTest() throws JSONException {

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/bucket/scan-item/2"), HttpMethod.GET, entity, String.class);

        HttpEntity<String> entitySession = sessionSetUp(response);

        ResponseEntity<String> response2 = restTemplate.exchange(createURLWithPort("/bucket/scan-item/2"), HttpMethod.GET, entitySession, String.class);

        String expected = "22.00";

        String body = response2.getBody();

        JSONAssert.assertEquals(expected, body, false);
    }

    @Test
    public void addTwoItemClearAndAddOneAgainTest() throws JSONException {

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/bucket/scan-item/1"), HttpMethod.GET, entity, String.class);
        HttpEntity<String> entitySession = sessionSetUp(response);
        restTemplate.exchange(createURLWithPort("/bucket/scan-item/2"), HttpMethod.GET, entitySession, String.class);
        ResponseEntity<String> responseTwoItem = restTemplate.exchange(createURLWithPort("/bucket/scan-item/3"), HttpMethod.GET, entitySession, String.class);

        String expected = "22.00";
        String body = responseTwoItem.getBody();
        JSONAssert.assertEquals(expected, body, false);

        ResponseEntity<String> checkBucket = restTemplate.exchange(createURLWithPort("/bucket/clean"), HttpMethod.GET, entitySession, String.class);

        String checkBucketExpectation = "0";
        String checkBucketBody = checkBucket.getBody();
        JSONAssert.assertEquals(checkBucketExpectation, checkBucketBody, false);

        ResponseEntity<String> newBucket = restTemplate.exchange(createURLWithPort("/bucket/scan-item/3"), HttpMethod.GET, entitySession, String.class);

        String newBucketExpectation = "7.00";
        String newBucketBody = newBucket.getBody();
        JSONAssert.assertEquals(newBucketExpectation, newBucketBody, false);
    }

    @Test
    public void promotionTest() throws JSONException {

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/bucket/scan-item/9"), HttpMethod.GET, entity, String.class);
        HttpEntity<String> entitySession = sessionSetUp(response);
        restTemplate.exchange(createURLWithPort("/bucket/scan-item/9"), HttpMethod.GET, entitySession, String.class);
        restTemplate.exchange(createURLWithPort("/bucket/scan-item/9"), HttpMethod.GET, entitySession, String.class);
        ResponseEntity<String> responseFourItem = restTemplate.exchange(createURLWithPort("/bucket/scan-item/9"), HttpMethod.GET, entitySession, String.class);

        String expected = "7.20";
        String body = responseFourItem.getBody();
        JSONAssert.assertEquals(expected, body, false);

        restTemplate.exchange(createURLWithPort("/bucket/scan-item/9"), HttpMethod.GET, entitySession, String.class);
        restTemplate.exchange(createURLWithPort("/bucket/scan-item/9"), HttpMethod.GET, entitySession, String.class);
        ResponseEntity<String> responseSevenItemToEnablePromotion = restTemplate.exchange(createURLWithPort("/bucket/scan-item/9"), HttpMethod.GET, entitySession, String.class);

        String expectedPromotionAmount = "12.00";
        String bodyOfSevenItem = responseSevenItemToEnablePromotion.getBody();
        JSONAssert.assertEquals(expectedPromotionAmount, bodyOfSevenItem, false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    private HttpEntity<String> sessionSetUp(ResponseEntity<String> response) {
        String sessionCookie = response.getHeaders().get("Set-Cookie").get(0).split(";")[0];
        HttpHeaders headersSession = new HttpHeaders();
        headersSession.add("Cookie", sessionCookie);
        return new HttpEntity<>(headersSession);
    }
}

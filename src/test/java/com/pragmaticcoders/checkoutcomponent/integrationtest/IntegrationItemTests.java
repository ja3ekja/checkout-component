package com.pragmaticcoders.checkoutcomponent.integrationtest;

import com.pragmaticcoders.checkoutcomponent.CheckoutComponentApplication;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
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
public class IntegrationItemTests {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();
    private HttpEntity<String> entity = new HttpEntity<>(null, headers);

    @Test
    public void getItemTest() throws JSONException {

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/item/get-item/1"), HttpMethod.GET, entity, String.class);

        String expected = "{id:1,name:juice,price:4.00}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void getPriceTest() throws JSONException {

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/item/get-price/6"), HttpMethod.GET, entity, String.class);

        String expected = "699.00";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void addItemTest() throws JSONException {

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/item/add-item/name/chips/price/2.00"), HttpMethod.GET, entity, String.class);

        String expected = "{id:10,name:chips,price:2.00}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}

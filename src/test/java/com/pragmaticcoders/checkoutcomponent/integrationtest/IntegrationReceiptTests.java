package com.pragmaticcoders.checkoutcomponent.integrationtest;

import com.pragmaticcoders.checkoutcomponent.CheckoutComponentApplication;
import com.pragmaticcoders.checkoutcomponent.exceptions.ReceiptNotExistException;
import com.pragmaticcoders.checkoutcomponent.model.Receipt;
import com.pragmaticcoders.checkoutcomponent.services.ReceiptService;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CheckoutComponentApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationReceiptTests {

    @LocalServerPort
    private int port;

    @Autowired
    private ReceiptService receiptService;

    private TestRestTemplate restTemplate = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();
    private HttpEntity<String> entity = new HttpEntity<>(headers);


    @Test
    public void addTwoItemClearAndAddOneAgainTest() throws JSONException, ReceiptNotExistException {

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/bucket/scan-item/1"), HttpMethod.GET, entity, String.class);
        HttpEntity<String> entitySession = sessionSetUp(response);
        restTemplate.exchange(createURLWithPort("/bucket/scan-item/2"), HttpMethod.GET, entitySession, String.class);
        restTemplate.exchange(createURLWithPort("/bucket/scan-item/3"), HttpMethod.GET, entitySession, String.class);

        restTemplate.exchange(createURLWithPort("/bucket/scan-item/3"), HttpMethod.GET, entitySession, String.class);
        restTemplate.exchange(createURLWithPort("/receipt/create-receipt"), HttpMethod.GET, entitySession, String.class);

        Receipt receipt = receiptService.getReceipt(1L);
        assertThat(receipt.getTransactionItems().size(),is(5));
        assertThat(receipt.getName().substring(0,3), is("FV-"));
    }

    @Test(expected = ReceiptNotExistException.class)
    public void exceptionWhenGetReceiptWhichNotExistTest() throws JSONException, ReceiptNotExistException {
        Receipt receipt = receiptService.getReceipt(30L);
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

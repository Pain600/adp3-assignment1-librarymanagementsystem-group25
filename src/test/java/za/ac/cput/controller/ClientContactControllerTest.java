package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import za.ac.cput.domain.ClientContact;
import za.ac.cput.factory.ClientContactFactory;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientContactControllerTest {
    @LocalServerPort
    private int portNumber;
    @Autowired private ClientContactController clientContactController;
    @Autowired
    private TestRestTemplate restTemplate;

    private ClientContact clientContact;
    private String urlbase;

  @BeforeEach
          void setUp(){
      assertNotNull(clientContactController);
      this.clientContact= ClientContactFactory.createClientcontact("345","76694");
      this.urlbase= "http:/localhost:" + this.portNumber+"/adp3-assignmentsystem-group25/clientcontact/";


  }

    @Test
    void create() {
      String url = urlbase +"save_clientcontact";
        System.out.println(url);

        ResponseEntity<ClientContact> response = this.restTemplate
                .postForEntity(url,this.clientContact,ClientContact.class);
        System.out.println(response);
        assertAll(
                () -> assertEquals(HttpStatus.OK,response.getStatusCode()),
                () -> assertNotNull(response.getBody()),
                () -> assertSame("345",clientContact.getContactId()),
                () -> assertSame("76694",clientContact.getClientId())
        );

    }

    @Test
    void read() {
        String url = urlbase +"readClientcontact/" + clientContact.getContactId();
        ResponseEntity<ClientContact> response = this.restTemplate.getForEntity(url,ClientContact.class);

        assertAll(
                () -> assertEquals(HttpStatus.OK,response.getStatusCode()),
                () -> assertNotNull(response.getBody()),
                () -> assertSame("345",clientContact.getContactId()),
                () -> assertSame("76694",clientContact.getClientId())
        );

    }

    @Test
    void update() {


    }

    @Test
    void delete() {
        String url = urlbase +"deleteClientcontact/" + clientContact.getContactId();
        this.restTemplate.delete(url);
        assertAll(

                () -> assertSame("345",clientContact.getContactId()),
                () -> assertSame("76694",clientContact.getClientId())
        );

    }

    @Test
    void getAll() {
        String url = urlbase +"getClientcontact/";
        System.out.println(url);

        ResponseEntity<ClientContact[]> response = this.restTemplate.getForEntity(url,ClientContact[].class);
        System.out.println(Arrays.asList(Objects.requireNonNull(response.getBody())));
        assertAll(
                () -> assertEquals(HttpStatus.OK,response.getStatusCode()),
                () -> assertTrue(response.getBody().length==0),
                () -> assertNotNull(response)
                );


    }
}
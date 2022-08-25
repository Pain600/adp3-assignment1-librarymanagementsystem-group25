package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.LibrarianContact;
import za.ac.cput.factory.LibrarianContactFactory;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LibrarianContactControllerTest {
    @LocalServerPort
    private int portNumber;
    @Autowired
    private LibrarianContactController librarianContactController;
    @Autowired
    private TestRestTemplate restTemplate;

    private LibrarianContact librarianContact;
    private String urlbase;

    @BeforeEach
    void setUp(){
        assertNotNull(librarianContact);
        this.librarianContact= LibrarianContactFactory.createLibrariancontact("345","76694");
        this.urlbase= "http:/localhost:" + this.portNumber+"/adp3-assignmentsystem-group25/librariancontact/";


    }

    @Test
    void create() {
        String url = urlbase +"savelibrariancontact";
        System.out.println(url);

        ResponseEntity<LibrarianContact> response = this.restTemplate
                .postForEntity(url,this.librarianContact,LibrarianContact.class);
        System.out.println(response);
        assertAll(
                () -> assertEquals(HttpStatus.OK,response.getStatusCode()),
                () -> assertNotNull(response.getBody()),
                () -> assertSame("345",librarianContact.getContactId()),
                () -> assertSame("76694",librarianContact.getLibrarianId())
        );

    }

    @Test
    void read() {
        String url = urlbase +"readLibrariancontact/" + librarianContact.getContactId();
        ResponseEntity<LibrarianContact> response = this.restTemplate.getForEntity(url,LibrarianContact.class);

        assertAll(
                () -> assertEquals(HttpStatus.OK,response.getStatusCode()),
                () -> assertNotNull(response.getBody()),
                () -> assertSame("345",librarianContact.getContactId()),
                () -> assertSame("76694",librarianContact.getLibrarianId())
        );

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
        String url = urlbase +"deleteLibrariancontact/" + librarianContact.getContactId();
        this.restTemplate.delete(url);
        assertAll(

                () -> assertSame("345",librarianContact.getContactId()),
                () -> assertSame("76694",librarianContact.getLibrarianId())
        );

    }

    @Test
    void getAll() {
        String url = urlbase +"getLibrariancontact/";
        System.out.println(url);

        ResponseEntity<LibrarianContact[]> response = this.restTemplate.getForEntity(url,LibrarianContact[].class);
        System.out.println(Arrays.asList(Objects.requireNonNull(response.getBody())));
        assertAll(
                () -> assertEquals(HttpStatus.OK,response.getStatusCode()),
                () -> assertTrue(response.getBody().length==0),
                () -> assertNotNull(response)
        );
    }
}
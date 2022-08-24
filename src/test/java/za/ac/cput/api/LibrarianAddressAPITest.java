package za.ac.cput.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.LibrarianAddress;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.CityFactory;
import za.ac.cput.factory.LibrarianAddressFactory;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class LibrarianAddressAPITest {

    private Address address;

    @Autowired
    private LibrarianAddressAPI librarianAddressAPI;

    private LibrarianAddress librarianAddress1,librarianAddress2;

    @BeforeEach
    void setUp() {
        librarianAddress1 = LibrarianAddressFactory.createLibrarianAddress("101",
                AddressFactory.createAddress("K18","Burgundy Estate","6","Bird Avenue",7100,
                        CityFactory.createCity("12","CPT","Northern")));

        librarianAddress2 = LibrarianAddressFactory.createLibrarianAddress("102",
                AddressFactory.createAddress("K20","Weltervreden","52","Voortrekker Road",7300,
                        CityFactory.createCity("MB002","Bellville","Southern")));


    }

    @Test
    void create() {
        librarianAddress1 = this.librarianAddressAPI.create(this.librarianAddress1);
        librarianAddress2 = this.librarianAddressAPI.create(this.librarianAddress2);

        assertAll(
                () -> assertNotNull(librarianAddress1),
                () -> assertNotNull(librarianAddress2),
                () -> assertNotSame(librarianAddress1,librarianAddress2),
                () -> assertNotSame(librarianAddress1.getLibrarianId(),librarianAddress2.getLibrarianId()),
                () -> assertNotEquals(librarianAddress1,librarianAddress2)
        );
    }

    @Test
    void read() {
        librarianAddress1 = this.librarianAddressAPI.read(this.librarianAddress1);

        assertAll(
                () -> assertNotNull(librarianAddress1),
                () -> assertNotSame(librarianAddress1,librarianAddress2)
        );

        System.out.println("Read Success...");
    }

    @Test
    void delete() {
        librarianAddress2 = this.librarianAddressAPI.delete(this.librarianAddress2);

        assertAll(
                () -> assertNotNull(librarianAddress2),
                () -> assertSame("102",librarianAddress2.getLibrarianId()),
                () -> assertSame("K20",librarianAddress2.getAddress().getUnitNumber()),
                () -> assertSame("MB002",librarianAddress2.getAddress().getCity().getId())
        );

        System.out.println("Deletion Success...");
    }
}
package za.ac.cput.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.City;
import za.ac.cput.factory.CityFactory;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class CityAPITest {

    @Autowired
    private CityAPI cityAPI;

    private City city1, city2;


    @BeforeEach
    void setUp() {
        city1 = CityFactory.createCity("12","CPT","Southern");
        city2 = CityFactory.createCity("18","JHB","Roodepoort");
    }

    @Test
    void create() {
        city1 = this.cityAPI.create(this.city1);
        city2 = this.cityAPI.create(this.city2);

        assertAll(
                () -> assertNotNull(city1),
                () -> assertNotNull(city2),
                () -> assertNotSame(city1,city2),
                () -> assertNotSame(city1.getId(),city2.getId()),
                () -> assertNotEquals(city1,city2)
        );
    }

    @Test
    void read() {
        city1 = this.cityAPI.read(this.city1);

        assertAll(
                () -> assertNotNull(city1),
                () -> assertNotSame(city1,city2)
        );

        System.out.println("Read Successful...");
    }

    @Test
    void delete() {
        city2 = this.cityAPI.delete(this.city2);
        assertAll(
                () -> assertNotNull(city2),
                () -> assertNotSame("18",city2.getId()),
                () -> assertNotSame("JHB",city2.getName()),
                () -> assertNotSame("Roodepoort",city2.getSuburb())
        );

        System.out.println("Deletion Success....");
    }
}
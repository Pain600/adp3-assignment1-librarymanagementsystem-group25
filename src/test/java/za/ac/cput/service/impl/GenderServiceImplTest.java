package za.ac.cput.service.impl;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import za.ac.cput.domain.Gender;
import za.ac.cput.factory.GenderFactory;
import za.ac.cput.repository.GenderRepository;
import za.ac.cput.service.GenderService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GenderServiceImplTest {

    @Autowired
    GenderServiceImpl genderService;


    private static Gender gender1 , gender2,  updateGender;


    @BeforeEach
    void setUp(){
        gender1 = GenderFactory.createGender("1" , "Male" , "Other");
        gender2 = GenderFactory.createGender("2" , "Female" , "Other");
        updateGender = new Gender.Builder().copy(gender1).name("Non-Binary").build();
    }

    @Test
    @Order(1)
    void create(){
        Gender created1 = genderService.create(gender1);
        assertNotNull(created1);
        System.out.println(created1);

        Gender created2 = genderService.create(gender2);
        assertNotNull(created2);
        System.out.println(created2);

    }

    @Test
    @Order(2)
    void read(){
        Gender read = genderService.read(gender1.getGenderId());
        assertEquals(read.getGenderId() , gender1.getGenderId());
        System.out.println("read" +read);
    }


    @Test
    @Order(3)
    void update(){
        Gender updated = genderService.create(updateGender);
        Gender created1 = genderService.create(gender1);

        assertSame(false ,gender1.getName(), updateGender.getName());
        System.out.println("Gender Updated Successfully");
    }

    @Test
    @Order(4)
    void delete(){
        genderService.delete(gender2.getGenderId());
        assertNotNull(gender2);
        System.out.println("Gender deleted successfully");

    }

    @Test
    @Order(5)
    void getAll(){
        System.out.println(genderService.getAll());
    }


}

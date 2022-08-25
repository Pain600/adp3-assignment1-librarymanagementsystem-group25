package za.ac.cput.repository;


/*
 Name : Ethan George
 Student Number : 218008430
 Project: Library Management
 Date : 2022/08/08
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Gender;

import java.util.List;

@Repository
public interface GenderRepository  extends JpaRepository <Gender, String> {
    List<Gender> findGenderById(String genderId);

}

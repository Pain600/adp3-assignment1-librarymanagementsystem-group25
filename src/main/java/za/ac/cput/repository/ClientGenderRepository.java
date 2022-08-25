package za.ac.cput.repository;


/*
 Name : Ethan George
 Student Number : 218008430
 Project: Library Management
 Date : 2022/08/08
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.ClientGender;

import java.util.List;

@Repository
public interface ClientGenderRepository extends JpaRepository<ClientGender , String>{
    List<ClientGender> findClientGenderById(String clientId);
}

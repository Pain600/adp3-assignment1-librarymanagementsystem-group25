package za.ac.cput.service;

import za.ac.cput.domain.ClientGender;
import za.ac.cput.domain.Gender;

import java.util.List;

public interface ClientGenderService extends IService<ClientGender , String> {


    public List<ClientGender> getAll();

    List<ClientGender> findClientGenderById(String clientId);
}

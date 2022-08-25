package za.ac.cput.service.impl;



import org.springframework.stereotype.Service;
import za.ac.cput.domain.Gender;
import za.ac.cput.repository.GenderRepository;
import za.ac.cput.service.GenderService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SubmissionPublisher;

@Service
public class GenderServiceImpl implements GenderService {


    private final GenderRepository genderRepository;

    public GenderServiceImpl(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @Override
    public Gender create(Gender gender){
        return this.genderRepository.save(gender);
    }

    @Override
    public Gender update(Gender gender){
        return this.genderRepository.save(gender);
    }

    @Override
    public Gender read(String id){
        return this.genderRepository.getReferenceById(id);
    }

    @Override
    public void delete(String id){
        if (this.genderRepository.existsById(id)){
            this.genderRepository.deleteById(id);
            System.out.println("Deleted Successfully");
        }else {
            System.out.println("Gender ID doesnt exist");
        }


    }

    @Override
    public List<Gender> getAll(){
        return this.genderRepository.findAll();
    }

    @Override
    public List<Gender> findGenderById(String genderId){
        List<Gender> subSet = this.genderRepository.findGenderById(genderId);

        System.out.println(subSet);
        List<Gender> genderNameList = new ArrayList<>();
        for(Gender b : subSet){
            genderNameList.add(b);
        }

        return genderNameList;
    }
}

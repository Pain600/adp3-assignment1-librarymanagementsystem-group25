package za.ac.cput.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.Gender;
import za.ac.cput.service.impl.GenderServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/library-management/genders")
public class GenderController {

    private final GenderServiceImpl genderService;


    public GenderController(GenderServiceImpl genderService) {
        this.genderService = genderService;
    }

    @GetMapping("/getAllGenders")
    public List<Gender> getAll(){
        return genderService.getAll().stream().toList();
    }

    @PostMapping("/save_gender")
    public ResponseEntity<Gender> create(@Valid @RequestBody Gender saveGender){
        try{
            Gender newGender = this.genderService.create(saveGender);
            return ResponseEntity.ok(newGender);
        }catch (IllegalArgumentException exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , exception.getMessage());
        }
    }

    @PostMapping("/update_gender")
    public ResponseEntity<Gender> update(@Valid @RequestBody Gender updateGender){

        try{
            Gender updatedGender = this.genderService.update(updateGender);
            return ResponseEntity.ok(updatedGender);
        }catch (IllegalArgumentException exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , exception.getMessage());
        }
    }

    @DeleteMapping("/deleteGender/{genderId}")
    public ResponseEntity<Gender> delete(@PathVariable String genderId){

        this.genderService.delete(genderId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getGender/{id}")
    public List<Gender> getGender(@PathVariable String id){
        return genderService.findGenderById(id);
    }


}

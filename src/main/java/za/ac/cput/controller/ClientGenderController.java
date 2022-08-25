package za.ac.cput.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.ClientBookId;
import za.ac.cput.domain.ClientGender;
import za.ac.cput.service.ClientGenderService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/library-management/clientgender")
public class ClientGenderController {

    private final ClientGenderService clientGenderService;

    public ClientGenderController(ClientGenderService clientGenderService) {
        this.clientGenderService = clientGenderService;
    }


    @GetMapping("/getAllClientGender")
    public List<ClientGender> getAll(){
        return clientGenderService.getAll().stream().toList();
    }

    @PostMapping("/save_clientGender")
    public ResponseEntity<ClientGender> create(@Valid @RequestBody ClientGender saveClientGender){

        try{
            ClientGender newClientGender = this.clientGenderService.create(saveClientGender);
            return ResponseEntity.ok(newClientGender);
        }catch (IllegalArgumentException exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , exception.getMessage());
        }
    }

    @PostMapping("/update_clientGender")
    public ResponseEntity<ClientGender> update(@Valid @RequestBody ClientGender updateClientGender){
        try {
            ClientGender updatedClientGender = this.clientGenderService.update(updateClientGender);
            return ResponseEntity.ok(updatedClientGender);
        }catch (IllegalArgumentException exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , exception.getMessage());
        }

    }

    @DeleteMapping("/deleteClientGender/{id}")
    public ResponseEntity<ClientGender> delete(@PathVariable String id){
        this.clientGenderService.delete((id));
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/getClientGender/{id}")
    public List<ClientGender> findClientGenderId(@PathVariable String id){
        return clientGenderService.findClientGenderById(id);
    }


}

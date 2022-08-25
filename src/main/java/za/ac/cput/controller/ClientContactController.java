package za.ac.cput.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.ClientContact;
import za.ac.cput.service.impl.ClientContactIService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("librarymanagementsystem/clientContact/")
@Slf4j

public class ClientContactController {

    private final ClientContactIService clientContactIService;


    public ClientContactController(ClientContactIService clientContactIService) {
        this.clientContactIService = clientContactIService;
    }

    @PostMapping("save_ClientContact")
    public ResponseEntity<ClientContact> create(@Valid @RequestBody ClientContact saveClientContact){
        log.info("Save request: {}" , saveClientContact);

        try{
            ClientContact clientContact = this.clientContactIService.create(saveClientContact);
            return ResponseEntity.ok(clientContact);
        }catch(IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @GetMapping("readClientContact/{contactId}")
    public ResponseEntity<ClientContact> read(@PathVariable String contactId){
        log.info("Read request: {}" ,contactId);

        try{
            ClientContact getClientContact = this.clientContactIService.read(contactId);
            return ResponseEntity.ok(getClientContact);

        }catch(IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @PostMapping("update_ClientContact")
    public ResponseEntity<ClientContact> update(@Valid @RequestBody ClientContact updateclientContact){
        log.info("update request:  {}",updateclientContact);

        try{
            ClientContact update = this.clientContactIService.update(updateclientContact);
            return ResponseEntity.ok(update);

        }catch(IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @DeleteMapping("deleteClientContact/{contactId}")
    public ResponseEntity<ClientContact> delete(@PathVariable String contactId){
        log.info("Delete request: {}",contactId);

        this.clientContactIService.delete(contactId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("getAll_ClientContacts")
    public ResponseEntity<List<ClientContact>> getAll(){
        List<ClientContact> clientContactList = this.clientContactIService.getAll();
        return ResponseEntity.ok(clientContactList);
    }

}

package za.ac.cput.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.cput.domain.LibrarianAddress;
import za.ac.cput.repository.LibrarianAddressIRepository;

import za.ac.cput.service.impl.LibrarianAddressService;

import java.util.Optional;

@Component
public class LibrarianAddressAPI {
    private final LibrarianAddressService librarianAddressService;

    private final LibrarianAddressIRepository librarianAddressIRepository;

    @Autowired
    public LibrarianAddressAPI(LibrarianAddressService librarianAddressService, LibrarianAddressIRepository librarianAddressIRepository){
        this.librarianAddressService = librarianAddressService;
        this.librarianAddressIRepository = librarianAddressIRepository;

    }

    public LibrarianAddress create(LibrarianAddress librarianAddress){
        Optional<LibrarianAddress> addLibrarianAddress = librarianAddressIRepository.findById(librarianAddress.getLibrarianId());




        if (addLibrarianAddress.isPresent()){
            throw new IllegalStateException("ID already exists");
        }

        return this.librarianAddressIRepository.save(librarianAddress);

    }

    public LibrarianAddress read(LibrarianAddress getLibrarianAddress){
        Optional<LibrarianAddress> librarianId = librarianAddressIRepository.findById(getLibrarianAddress.getLibrarianId());


        if (librarianId.isEmpty()){
            throw new IllegalStateException("Librarian Address not found");
        }

        return this.librarianAddressService.read(getLibrarianAddress.getLibrarianId() + getLibrarianAddress.getAddress());
    }

    public LibrarianAddress delete(LibrarianAddress deleteLibrarianAddress){
        Optional<LibrarianAddress> librarianId = librarianAddressIRepository.findById(deleteLibrarianAddress.getLibrarianId());


        if (librarianId.isPresent()){
            this.librarianAddressService.delete(deleteLibrarianAddress.getLibrarianId());
        }else{
            throw new IllegalStateException("Librarian Address Not Found");
        }

        return deleteLibrarianAddress;
    }


}

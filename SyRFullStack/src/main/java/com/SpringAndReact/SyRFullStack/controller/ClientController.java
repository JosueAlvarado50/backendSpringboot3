package com.SpringAndReact.SyRFullStack.controller;

import com.SpringAndReact.SyRFullStack.dto.ClientDto;
import com.SpringAndReact.SyRFullStack.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/food-order-app/client")
@CrossOrigin("*")
@RestController
public class ClientController {
    private ClientService clientService;

    /**
     *
     * @param clientDto- seas mamon- que showsjnnknijnknkjn
     * @return
     */
    @PostMapping
    public ResponseEntity<ClientDto> addClient(@RequestBody ClientDto clientDto){
        ClientDto createdClient = clientService.addClient(clientDto);
        return new ResponseEntity<>(createdClient, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> getAllClients(){
        List<ClientDto> clientDtoList = clientService.getAllClients();
        return new ResponseEntity<>(clientDtoList, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable(name = "id") Long clientId){
        ClientDto clientDto = clientService.getClient(clientId);
        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable(name = "id") Long clientId, @RequestBody ClientDto clientDto){
        ClientDto updatedClient = clientService.updateClient(clientId, clientDto);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteClient(@PathVariable(name = "id") Long clientId){
        clientService.deleteClient(clientId);
        return ResponseEntity.ok("Client was deleted successfully");
    }
}

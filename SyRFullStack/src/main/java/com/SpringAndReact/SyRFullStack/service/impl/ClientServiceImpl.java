package com.SpringAndReact.SyRFullStack.service.impl;

import com.SpringAndReact.SyRFullStack.dto.ClientDto;
import com.SpringAndReact.SyRFullStack.entity.Client;
import com.SpringAndReact.SyRFullStack.exception.ResourceNotFoundException;
import com.SpringAndReact.SyRFullStack.repository.ClientRepository;
import com.SpringAndReact.SyRFullStack.service.ClientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;
    private ModelMapper modelMapper;

    @Override
    public ClientDto addClient(ClientDto clientDto) {
        //primero le damos formato al client que sera instancia
        Client client = modelMapper.map(clientDto, Client.class);
        //ahora creamos el cliente en la DB
        Client savedClient = clientRepository.save(client);
        return modelMapper.map(savedClient, ClientDto.class);
    }

    @Override
    public ClientDto getClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Client not found: " + clientId)
                );
        return modelMapper.map(client, ClientDto.class);
    }

    @Override
    public List<ClientDto> getAllClients() {
        List<Client> clientList = clientRepository.findAll();
        return clientList.stream()
                .map((client) -> modelMapper
                        .map(client, ClientDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ClientDto updateClient(Long clientId, ClientDto clientDto) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Client not found: "+ clientId)
                );
        client.setName(clientDto.getName());
        client.setPhone_Number(clientDto.getPhone_Number());
        client.setStreet(clientDto.getStreet());
        client.setPostal_Code(clientDto.getPostal_Code());
        client.setCity(clientDto.getCity());
        Client updatedClient = clientRepository.save(client);
        return modelMapper.map(updatedClient, ClientDto.class);
    }

    @Override
    public void deleteClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("client not found: "+clientId)
                );
        clientRepository.deleteById(clientId);

    }
}

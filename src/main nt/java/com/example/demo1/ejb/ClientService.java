package com.example.demo1.ejb;

import com.example.demo1.model.Address;
import com.example.demo1.model.Client;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class ClientService {
    @PersistenceContext
    private EntityManager em;

    public void createClient(Client client, Address address) {
        // Устанавливаем связь между адресом и клиентом
        address.setClient(client);
        client.setAddresses(List.of(address));

        // Сохраняем клиента, каскад сохранит адрес
        em.persist(client);
    }
}
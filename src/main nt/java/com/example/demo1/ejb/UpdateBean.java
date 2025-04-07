package com.example.demo1.ejb;


import com.example.demo1.model.Client;
import com.example.demo1.util.ValidationUtil;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ValidationException;

@Stateless
public class UpdateBean {
    @PersistenceContext
    private EntityManager em;

    public void createClient(Client client) throws ValidationException {
        ValidationUtil.validateClient(client);
        em.persist(client);
    }

    public void updateClient(Client client) throws ValidationException {
        ValidationUtil.validateClient(client);
        em.merge(client);
    }

    public void deleteClient(Long clientId) {
        Client client = em.find(Client.class, clientId);
        if (client != null) {
            em.remove(client);
        }
    }
}
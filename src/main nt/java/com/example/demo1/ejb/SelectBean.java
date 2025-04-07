package com.example.demo1.ejb;


import com.example.demo1.model.Client;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class SelectBean {
    @PersistenceContext
    private EntityManager em;

    public List<Client> findAllClients() {
        return em.createNamedQuery("Client.findAll", Client.class).getResultList();
    }

    public Client findClientById(Long id) {
        return em.find(Client.class, id);
    }

    public List<Client> findByName(String name) {
        TypedQuery<Client> query = em.createNamedQuery("Client.findByName", Client.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    public List<Client> findByType(String type) {
        TypedQuery<Client> query = em.createNamedQuery("Client.findByType", Client.class);
        query.setParameter("type", type);
        return query.getResultList();
    }

    public List<Client> searchByNameAndAddress(String searchText) {
        // Более сложный запрос с join и поиском по нескольким полям
        String jpql = "SELECT DISTINCT c FROM Client c LEFT JOIN c.addresses a " +
                      "WHERE c.clientName LIKE :search OR a.address LIKE :search";
        return em.createQuery(jpql, Client.class)
                .setParameter("search", "%" + searchText + "%")
                .getResultList();
    }
}
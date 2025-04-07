package com.example.demo1.ejb;

import com.example.demo1.model.Client;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class SelectBean {

    @PersistenceContext(unitName = "ClientPU")
    private EntityManager em;

    public List<Client> getClientsFiltered(String type, String search) {
        String queryStr = "SELECT DISTINCT c FROM Client c LEFT JOIN FETCH c.addresses a WHERE 1=1";

        if (type != null && !type.isEmpty()) {
            queryStr += " AND c.type = :type";
        }
        if (search != null && !search.isEmpty()) {
            queryStr += " AND (LOWER(c.clientName) LIKE :search OR LOWER(a.address) LIKE :search)";
        }

        TypedQuery<Client> query = em.createQuery(queryStr, Client.class);

        if (type != null && !type.isEmpty()) {
            query.setParameter("type", type);
        }
        if (search != null && !search.isEmpty()) {
            query.setParameter("search", "%" + search.toLowerCase() + "%");
        }

        return query.getResultList();
    }
}
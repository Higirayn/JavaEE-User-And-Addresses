package com.example.demo1.ejb;

import com.example.demo1.model.Address;
import com.example.demo1.model.Client;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Stateless
public class UpdateBean {

    @PersistenceContext(unitName = "ClientPU")
    private EntityManager em;

    public void createClient(String name, String type, String ip, String mac, String model, String addressText) {
        Client client = new Client();
        client.setClient_name(name);
        client.setType(type);
        client.setAdded(new Date());

        Address address = new Address();
        address.setIp(ip);
        address.setMac(mac);
        address.setModel(model);
        address.setAddress(addressText);
        address.setClient(client);

        client.setAddresses(Collections.singletonList(address));

        em.persist(client);
    }

    public void updateClient(int id, String name, String type, Date added, String[] ips, String[] macs, String[] models, String[] addresses) {
        Client client = em.find(Client.class, id);
        if (client != null) {
            client.setClient_name(name);
            client.setType(type);
            client.setAdded(added);

            // Update addresses
            List<Address> existingAddresses = client.getAddresses();
            for (int i = 0; i < ips.length; i++) {
                if (i < existingAddresses.size()) {
                    // Update existing address
                    Address address = existingAddresses.get(i);
                    address.setIp(ips[i]);
                    address.setMac(macs[i]);
                    address.setModel(models[i]);
                    address.setAddress(addresses[i]);
                    em.merge(address);
                } else {
                    // Add new address
                    Address newAddress = new Address(ips[i], macs[i], models[i], addresses[i], client);
                    em.persist(newAddress);
                    existingAddresses.add(newAddress);
                }
            }

            // Remove any extra addresses if new list is shorter
            if (ips.length < existingAddresses.size()) {
                for (int i = ips.length; i < existingAddresses.size(); i++) {
                    em.remove(existingAddresses.get(i));
                }
                existingAddresses.subList(ips.length, existingAddresses.size()).clear();
            }

            em.merge(client);
        }
    }

    public void deleteClient(int id) {
        Client client = em.find(Client.class, id);
        if (client != null) {
            em.remove(client);
        }
    }

    public Client findClientById(int id) {
        return em.find(Client.class, id);
    }

    // Аналогично: updateClient, deleteClient ...
}
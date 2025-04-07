package com.example.demo1.model;

import com.example.demo1.model.Address;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientid;

    @Column(length = 100, nullable = false)
    private String client_name;

    @Column(length = 40, nullable = false)
    private String type;

    @Temporal(TemporalType.DATE)
    private Date added;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Address> addresses = new ArrayList<>();

    public Client(int clientid, String client_name, String type, Date added, List<Address> addresses) {
        this.clientid = clientid;
        this.client_name = client_name;
        this.type = type;
        this.added = added;
        this.addresses = addresses;
    }

    public Client(String client_name, String type, Date added, List<Address> addresses) {
        this.client_name = client_name;
        this.type = type;
        this.added = added;
        this.addresses = addresses;
    }

    public Client() {

    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}

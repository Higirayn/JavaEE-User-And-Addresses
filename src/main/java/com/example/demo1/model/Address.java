package com.example.demo1.model;

import com.example.demo1.model.Client;
import jakarta.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 25, nullable = false)
    private String ip;

    public Address() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Address(String ip, String mac, String model, String address, Client client) {
        this.ip = ip;
        this.mac = mac;
        this.model = model;
        this.address = address;
        this.client = client;
    }

    public Address(int id, String ip, String mac, String model, String address, Client client) {
        this.id = id;
        this.ip = ip;
        this.mac = mac;
        this.model = model;
        this.address = address;
        this.client = client;
    }

    @Column(length = 20, nullable = false)
    private String mac;

    @Column(length = 100, nullable = false)
    private String model;

    @Column(length = 200, nullable = false)
    private String address;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
}
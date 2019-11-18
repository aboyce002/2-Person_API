package com.aboyce002.Person_API.domains;

import org.apache.tomcat.jni.Address;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Set;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    private String first_name;
    private String last_name;
    private Set<Address> address;

    public Customer(Long id, String first_name, String last_name, Set<Address> address) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", address=" + address +
                '}';
    }
}

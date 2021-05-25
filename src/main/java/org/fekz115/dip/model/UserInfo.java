package org.fekz115.dip.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
public class UserInfo {
    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    @OneToOne
    private Picture picture;
    @OneToOne
    private Address address;
}

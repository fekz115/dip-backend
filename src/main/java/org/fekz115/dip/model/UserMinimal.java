package org.fekz115.dip.model;

import lombok.Data;

@Data
public class UserMinimal {
    private int id;
    private String login;
    private String firstName;
    private String lastName;
    private Picture avatar;
}

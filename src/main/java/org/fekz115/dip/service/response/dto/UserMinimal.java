package org.fekz115.dip.service.response.dto;

import lombok.Data;
import org.fekz115.dip.model.Picture;
import org.fekz115.dip.model.User;

@Data
public class UserMinimal {
    public UserMinimal(User user) {
        id = user.getId();
        login = user.getLogin();
        if(user.getUserInfo() != null) {
            avatar = user.getUserInfo().getPicture();
            firstName = user.getUserInfo().getFirstName();
            lastName = user.getUserInfo().getLastName();
        } else {
            avatar = null;
            firstName = null;
            lastName = null;
        }
    }

    private final int id;
    private final String login;
    private final String firstName;
    private final String lastName;
    private final Picture avatar;
}

package org.fekz115.dip.util;

import org.fekz115.dip.model.RatingState;
import org.fekz115.dip.model.User;

import java.util.Set;

public class DtoUtils {

    public static RatingState getRatingState(User user, Set<User> likes, Set<User> dislikes) {
        RatingState ratingState = RatingState.UNRATED;
        if(user != null) {
            if (likes.contains(user)) {
                ratingState = RatingState.LIKED;
            } else if (dislikes.contains(user)) {
                ratingState = RatingState.DISLIKED;
            }
        }
        return ratingState;
    }

}

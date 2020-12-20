package com.explore.learnings.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = true)
@Getter
@Setter
public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private String email;

}

package com.explore.learnings.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

@Accessors(fluent = true, chain = true)
@Getter
@Setter
@JsonSerialize
public class User {

    @Id
    private int userId;
    private String firstName;
    private String lastName;
    private String email;

}

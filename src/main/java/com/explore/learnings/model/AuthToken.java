package com.explore.learnings.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@JsonSerialize
@Accessors(fluent = true, chain = true)
public class AuthToken {

    private Integer userId;
    private String token;
    private Long expiry;
}

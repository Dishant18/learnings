package com.explore.learnings.model;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true, chain = true)
public class AuthToken {

    private String token;
    private Long expiry;
}

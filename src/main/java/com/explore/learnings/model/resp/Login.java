package com.explore.learnings.model.resp;

import com.explore.learnings.model.AuthToken;
import com.explore.learnings.model.User;
import lombok.Getter;
import lombok.experimental.Accessors;

// Discard this!
@Getter
@Accessors(fluent = true, chain = true)
public class Login extends User {

    private AuthToken token;
}

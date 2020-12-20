package com.explore.learnings.model.req;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Getter
@Accessors(chain = true, fluent = true)
public class LoginReq {

    private final @NonNull String email;
    private final @NonNull String pwd;
}

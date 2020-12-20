package com.explore.learnings.model.req;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Getter
@Accessors(chain = true, fluent = true)
public class SignUpReq {

    private final @NonNull String email;
    private final @NonNull String firstName;
    private final @NonNull String lastName;
    private final @NonNull String pwd;

}

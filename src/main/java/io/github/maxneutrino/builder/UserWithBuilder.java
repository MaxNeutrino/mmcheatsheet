package io.github.maxneutrino.builder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserWithBuilder {
    private final String firstName;
    private final String lastName;
}

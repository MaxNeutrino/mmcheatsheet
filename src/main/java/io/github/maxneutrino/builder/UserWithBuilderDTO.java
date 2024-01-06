package io.github.maxneutrino.builder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class UserWithBuilderDTO {
    private final String firstName;
    private final String secondName;
}

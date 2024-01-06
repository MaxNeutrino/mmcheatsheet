package io.github.maxneutrino;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String firstName;
    private String secondName;
    private String subscriptions;
    private LocationDTO location;
}

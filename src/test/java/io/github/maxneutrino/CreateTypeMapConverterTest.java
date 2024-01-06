package io.github.maxneutrino;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

class CreateTypeMapConverterTest {

    private final CreateTypeMapConverter converter = new CreateTypeMapConverter(new ModelMapper());

    @Test
    void convert() {
        var user = new User("Name", "Last Name", null, null, null);
        var actual = converter.convert(user);
        assertEquals(new UserDTO(user.getFirstName(), user.getLastName(), null, null), actual);
    }
}
package io.github.maxneutrino.builder;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

class BuilderConverterTest {

    private final BuilderConverter converter = new BuilderConverter(new ModelMapper());

    @Test
    void convert() {
        var user = new UserWithBuilder("Name", "Last Name");
        assertEquals(new UserWithBuilderDTO(user.getFirstName(), user.getLastName()), converter.convert(user));
    }
}
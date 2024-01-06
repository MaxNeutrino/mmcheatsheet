package io.github.maxneutrino;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleConverterTest {

    private final SimpleConverter converter = new SimpleConverter(new ModelMapper());

    @Test
    void convert() {
        var user = new User("Name", "Last Name", null, null, null);
        var actual = converter.convert(user);
        assertEquals(new UserDTO(user.getFirstName(), user.getLastName(), null, null), actual);
    }

    @Test
    void convertWithUsing() {
        var user = new User("Name", "Last Name", List.of("one", "two"), null, null);
        var actual = converter.convertWithUsing(user);
        assertEquals(new UserDTO(user.getFirstName(), user.getLastName(), "one,two", null), actual);
    }

    @Test
    void convertAddMappingWithConversion() {
        var user = new User("Name", "Last Name", List.of("one", "two"), null, null);
        assertThrows(NullPointerException.class, () -> converter.convertAddMappingWithConversion(user));
    }

    @Test
    void convertNestedSetter() {
        var user = new User("Name", "Last Name", null, "Ukraine", "Kyiv");
        var actual = converter.convertNestedSetter(user);
        assertEquals(new UserDTO(user.getFirstName(), user.getLastName(), null,
                new LocationDTO(user.getCountry(), user.getCity())), actual);
    }

    @Test
    void convertWithPreConverter() {
        var user = new User("Name", "Last Name", null, null, null);
        var actual = converter.convertWithPreConverter(user);
        assertEquals(new UserDTO("Joe", user.getLastName(), null, null), actual);
    }

    @Test
    void convertWithPostConverter() {
        var user = new User("Name", "Last Name", null, "Ukraine", "Kyiv");
        var actual = converter.convertWithPostConverter(user);
        assertEquals(new UserDTO(user.getFirstName(), user.getLastName(), null,
                new LocationDTO(user.getCountry(), user.getCity())), actual);
    }
}
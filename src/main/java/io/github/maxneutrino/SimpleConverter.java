package io.github.maxneutrino;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;

import java.util.List;

public class SimpleConverter {

    private final ModelMapper modelMapper;

    public SimpleConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDTO convert(User user) {
        return modelMapper.typeMap(User.class, UserDTO.class)
                .addMapping(User::getLastName, UserDTO::setSecondName)
                .map(user);
    }

    public UserDTO convertWithUsing(User user) {
        return modelMapper.typeMap(User.class, UserDTO.class)
                .addMappings(mapper -> {
                    mapper.map(User::getLastName, UserDTO::setSecondName);
                    mapper.using((MappingContext<List<String>, String> ctx) -> ctx.getSource() == null ? null : String.join(",", ctx.getSource()))
                            .map(User::getSubscriptions, UserDTO::setSubscriptions);
                })
                .map(user);
    }

    // will throw NPE as o is null
    public UserDTO convertAddMappingWithConversion(User user) {
        return modelMapper.typeMap(User.class, UserDTO.class)
                .addMapping(User::getLastName, UserDTO::setSecondName)
                .addMapping(User::getSubscriptions, (UserDTO dest, List<String> o) -> dest.setSubscriptions(String.join(",", o)))
                .map(user);
    }

    public UserDTO convertNestedSetter(User user) {
        return modelMapper.typeMap(User.class, UserDTO.class)
                .addMapping(User::getLastName, UserDTO::setSecondName)
                .addMapping(User::getCountry, (UserDTO dest, String v) -> dest.getLocation().setCountry(v))
                .addMapping(User::getCity, (UserDTO dest, String v) -> dest.getLocation().setCity(v))
                .map(user);
    }

    public UserDTO convertWithPreConverter(User user) {
        return modelMapper.typeMap(User.class, UserDTO.class)
                .setPreConverter(context -> {
                    context.getSource().setFirstName("Joe");
                    return context.getDestination();
                })
                .addMapping(User::getLastName, UserDTO::setSecondName)
                .map(user);
    }

    public UserDTO convertWithPostConverter(User user) {
        return modelMapper.typeMap(User.class, UserDTO.class)
                .setPostConverter(context -> {
                    var location = new LocationDTO(context.getSource().getCountry(), context.getSource().getCity());
                    context.getDestination().setLocation(location);
                    return context.getDestination();
                })
                .addMapping(User::getLastName, UserDTO::setSecondName)
                .map(user);
    }
}

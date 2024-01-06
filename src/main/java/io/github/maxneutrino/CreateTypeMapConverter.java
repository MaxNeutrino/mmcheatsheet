package io.github.maxneutrino;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class CreateTypeMapConverter {

    private final ModelMapper modelMapper;

    public CreateTypeMapConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        // can be moved to a configuration class
        var typeMap = modelMapper.createTypeMap(User.class, UserDTO.class);
        typeMap.addMapping(User::getLastName, UserDTO::setSecondName);
    }

    public UserDTO convert(User user) {
        return modelMapper.getTypeMap(User.class, UserDTO.class).map(user);
    }
}

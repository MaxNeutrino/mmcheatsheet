package io.github.maxneutrino;

import org.modelmapper.ModelMapper;

public class LazyTypeMapConverter {

    private final ModelMapper modelMapper;

    public LazyTypeMapConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDTO convert(User user) {
        var typeMap = modelMapper.getTypeMap(User.class, UserDTO.class);
        if (typeMap == null) {
            typeMap = modelMapper.createTypeMap(User.class, UserDTO.class);
            typeMap.addMapping(User::getLastName, UserDTO::setSecondName);
        }
        return typeMap.map(user);
    }
}

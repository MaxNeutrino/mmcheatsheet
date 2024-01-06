package io.github.maxneutrino.builder;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NameTransformers;
import org.modelmapper.convention.NamingConventions;

public class BuilderConverter {

    private final ModelMapper modelMapper;

    public BuilderConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        var config = modelMapper.getConfiguration().copy()
                .setDestinationNameTransformer(NameTransformers.builder())
                .setDestinationNamingConvention(NamingConventions.builder());
        var typeMap = modelMapper.createTypeMap(UserWithBuilder.class, UserWithBuilderDTO.UserWithBuilderDTOBuilder.class, config);
        typeMap.addMapping(UserWithBuilder::getLastName, UserWithBuilderDTO.UserWithBuilderDTOBuilder::secondName);

    }

    public UserWithBuilderDTO convert(UserWithBuilder user) {
        return modelMapper.getTypeMap(UserWithBuilder.class, UserWithBuilderDTO.UserWithBuilderDTOBuilder.class)
                .map(user).build();
    }
}

package com.ecommerce.backend.mappers;

import com.ecommerce.backend.dtos.requests.UserRequestDTO;
import com.ecommerce.backend.dtos.responses.UserResponseDTO;
import com.ecommerce.backend.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    //User userFromRequest(UserRequestDTO request);

    //UserResponseDTO responseFromUser(User user);

    void updateUserFromRequest(UserRequestDTO request, @MappingTarget User user);

    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    User userFromRequest(UserRequestDTO userRequestDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    UserResponseDTO responseFromUser(User user);
}

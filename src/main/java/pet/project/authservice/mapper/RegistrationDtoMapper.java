package pet.project.authservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pet.project.authservice.dto.request.RegistrationDtoRequest;
import pet.project.authservice.dto.touserservice.request.RegistrationDtoUSRequest;
import pet.project.authservice.util.PasswordUtil;

@Mapper
public interface RegistrationDtoMapper {

    RegistrationDtoMapper INSTANCE = Mappers.getMapper(RegistrationDtoMapper.class);

    @Mapping(source = "password", target = "passwordHash", qualifiedByName = "convertPasswordToHash")
    RegistrationDtoUSRequest registrationDtoRequestToUSRequest(RegistrationDtoRequest request);

    default String convertPasswordToHash(String password) {
        return PasswordUtil.hashPassword(password);
    }
}

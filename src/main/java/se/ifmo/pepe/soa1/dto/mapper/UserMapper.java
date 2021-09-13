package se.ifmo.pepe.soa1.dto.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import se.ifmo.pepe.soa1.domain.Role;
import se.ifmo.pepe.soa1.domain.User;
import se.ifmo.pepe.soa1.dto.request.CreateUserRequest;
import se.ifmo.pepe.soa1.dto.response.AuthUserView;
import se.ifmo.pepe.soa1.dto.response.UserView;

import static java.util.stream.Collectors.toSet;

@Mapper(componentModel = "spring")
@Component
public abstract class UserMapper {

    @Mapping(target = "authorities", ignore = true)
    public abstract User create(CreateUserRequest request);

    public abstract UserView toUserView(User user);
    public abstract AuthUserView toAuthUserView(User user);

    @AfterMapping
    protected void afterCreate(CreateUserRequest request, @MappingTarget User user) {
        if (request.getAuthorities() != null) {
            user.setAuthorities(request.getAuthorities().stream().map(Role::new).collect(toSet()));
        }
    }

    @AfterMapping
    protected void afterUpdate(CreateUserRequest request, @MappingTarget User user) {
        if (request.getAuthorities() != null) {
            user.setAuthorities(request.getAuthorities().stream().map(Role::new).collect(toSet()));
        }
    }
}
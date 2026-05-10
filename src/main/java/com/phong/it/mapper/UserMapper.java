package com.phong.it.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.phong.it.dto.request.UserRegisterRequestDTO;
import com.phong.it.dto.response.UserResponseDTO;
import com.phong.it.entity.Role;
import com.phong.it.entity.User;

@Mapper(componentModel = "jakarta", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    // MapStruct sẽ tự động gọi hàm mapRoleToString bên dưới để map Set<Role> sang Set<String>
    UserResponseDTO toResponseDTO(User user);

    // Thuộc tính confirmPassword nằm ở Source (DTO), và Target (User) không có thuộc tính này 
    // nên MapStruct sẽ tự động bỏ qua mà không gây lỗi.
    User toEntity(UserRegisterRequestDTO dto);

    // Hàm phụ trợ giúp MapStruct biết cách chuyển từ Object Role sang String
    default String mapRoleToString(Role role) {
        if (role == null) {
            return null;
        }
        return role.getName();
    }
}

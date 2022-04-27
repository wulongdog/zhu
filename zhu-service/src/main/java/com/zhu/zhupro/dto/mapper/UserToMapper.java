package com.zhu.zhupro.dto.mapper;

import com.zhu.zhupro.dto.UserDto;
import com.zhu.zhupro.pojo.UserBase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserToMapper {


    @Mapping(source = "id",target = "id")
    @Mapping(source = "background",target = "background")
    UserDto toDto(UserBase userBase);

}

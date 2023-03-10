package com.pagination.paginationdemo.service;

import com.pagination.paginationdemo.dto.UserDto;
import com.pagination.paginationdemo.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConverterService {
    @Autowired
    private ModelMapper modelMapper;

    // convert entity to dto
    public UserDto convertToDto(User userObject) {
        return modelMapper.map(userObject, UserDto.class);
    }

    // convert dto to entity
    public User convertToEntity(UserDto userdto) {
        return modelMapper.map(userdto, User.class);
    }
}

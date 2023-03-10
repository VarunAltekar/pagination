package com.pagination.paginationdemo.controller;

import com.pagination.paginationdemo.dto.UserDto;
import com.pagination.paginationdemo.entity.User;
import com.pagination.paginationdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //@Autowired
    //private PagedResourcesAssembler<User> pagedResourcesAssembler;

    @GetMapping(value="/allUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDto>> fetchAllUser(){
        return new ResponseEntity<List<UserDto>>(userService.getAllUser(), HttpStatus.OK);
    }

    @GetMapping(value = "/pagedUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDto>> fetchUserPage( @PageableDefault(page = 0, size = 2) Pageable pageable){
        return new ResponseEntity<List<UserDto>>(userService.getPageForUser(pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/pagedUserWithDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<User>> fetchUserPageWithPageableDetails(@PageableDefault(page = 0, size = 2) Pageable pageable){
        return new ResponseEntity<Page<User>>(userService.getPageForUserWithDetails(pageable), HttpStatus.OK);
    }
}

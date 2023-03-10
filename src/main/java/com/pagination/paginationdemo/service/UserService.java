package com.pagination.paginationdemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.pagination.paginationdemo.dto.UserDto;
import com.pagination.paginationdemo.entity.User;
import com.pagination.paginationdemo.repo.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConverterService converterService;

    public List<UserDto> getAllUser() {
        List<User> userList = Arrays.asList(
                new User("111", "First Demo"),
                new User("222", "Second Demo"),
                new User("333", "Third Demo"),
                new User("444", "Fourth Demo"),
                new User("555", "Fifth Demo"),
                new User("666", "Sixth Demo"),
                new User("777", "Seventh Demo"),
                new User("888", "Eighth Demo"));
        userRepository.saveAll(userList);
        return userRepository.findAll().stream().map(converterService::convertToDto).collect(Collectors.toList());

    }

    public List<UserDto> getPageForUser(Pageable pageable) {
        List<User> userList = Arrays.asList(
                new User("111", "First Demo"),
                new User("222", "Second Demo"),
                new User("333", "Third Demo"),
                new User("444", "Fourth Demo"),
                new User("555", "Fifth Demo"),
                new User("666", "Sixth Demo"),
                new User("777", "Seventh Demo"),
                new User("888", "Eighth Demo"));
        userRepository.saveAll(userList);

        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.getContent().stream().map(converterService::convertToDto).collect(Collectors.toList());

    }

    public Page<User> getPageForUserWithDetails(Pageable pageable) {
        List<User> userList = Arrays.asList(
                new User("111", "First Demo"),
                new User("222", "Second Demo"),
                new User("333", "Third Demo"),
                new User("444", "Fourth Demo"),
                new User("555", "Fifth Demo"),
                new User("666", "Sixth Demo"),
                new User("777", "Seventh Demo"),
                new User("888", "Eighth Demo"));
        userRepository.saveAll(userList);

        return userRepository.findAll(pageable);
    }
}

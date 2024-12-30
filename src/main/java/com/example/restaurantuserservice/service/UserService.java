package com.example.restaurantuserservice.service;

import com.example.restaurantuserservice.dto.TokenRequestDto;
import com.example.restaurantuserservice.dto.TokenResponseDto;
import com.example.restaurantuserservice.dto.UserCreateDto;
import com.example.restaurantuserservice.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserDto> findAll(Pageable pageable);

    UserDto add(UserCreateDto userCreateDto);
    TokenResponseDto login(TokenRequestDto tokenRequestDto);

}

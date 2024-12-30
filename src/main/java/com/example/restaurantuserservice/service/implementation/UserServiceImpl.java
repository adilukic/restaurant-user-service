package com.example.restaurantuserservice.service.implementation;

import com.example.restaurantuserservice.domain.Client;
import com.example.restaurantuserservice.domain.User;
import com.example.restaurantuserservice.dto.*;
import com.example.restaurantuserservice.exception.NotFoundException;
import com.example.restaurantuserservice.mapper.UserMapper;
import com.example.restaurantuserservice.repository.UserRepo;
import com.example.restaurantuserservice.security.service.TokenService;
import com.example.restaurantuserservice.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private TokenService tokenService;
    private UserRepo userRepo;
    private UserMapper userMapper;

    public UserServiceImpl(TokenService tokenService, UserRepo userRepo, UserMapper userMapper) {
        this.tokenService = tokenService;
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return userRepo.findAll(pageable)
                .map(userMapper::userToUserDto);
    }

    @Override
    public UserDto add(UserCreateDto userCreateDto) {

        User user = userMapper.userCreateDtoToUser(userCreateDto);
        userRepo.save(user);
        return userMapper.userToUserDto(user);

    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        User user = userRepo.findUserByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String.format("User with username: %s and password: %s not found.", tokenRequestDto.getUsername(),
                        tokenRequestDto.getPassword())));
        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("role", user.getRole().getName());
        return new TokenResponseDto(tokenService.generate(claims));
    }
}

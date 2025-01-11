package com.example.restaurantuserservice.service.implementation;

import com.example.restaurantuserservice.domain.Client;
import com.example.restaurantuserservice.domain.User;
import com.example.restaurantuserservice.dto.*;
import com.example.restaurantuserservice.exception.NotFoundException;
import com.example.restaurantuserservice.mapper.UserMapper;
import com.example.restaurantuserservice.repository.UserRepo;
import com.example.restaurantuserservice.security.service.TokenService;
import com.example.restaurantuserservice.service.NotificationSender;
import com.example.restaurantuserservice.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private TokenService tokenService;
    private UserRepo userRepo;
    private UserMapper userMapper;
    @Autowired
    private NotificationSender notificationSender;

    public UserServiceImpl(TokenService tokenService, UserRepo userRepo, UserMapper userMapper) {
        this.tokenService = tokenService;
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    @Override
    public Page<ClientDto> findAll(Pageable pageable) {
        return userRepo.findAll(pageable)
                .map(userMapper::userToUserDto);
    }

    @Override
    public ClientDto getUser(Long id) {
        Client client = (Client) userRepo.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        return userMapper.clientToClientDto(client);
    }


    @Override
    public ClientDto add(ClientCreateDto userCreateDto) {

        Client user = userMapper.userCreateDtoToUser(userCreateDto);
        userRepo.save(user);
        NotificationDto notificationDto = new NotificationDto(user.getId(), user.getEmail(), "ACTIVATION_EMAIL");
        notificationSender.sendNotification(notificationDto);
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

    @Override
    public ClientDto updateClient(Long id, ClientCreateDto userCreateDto) {
        Client client = (Client) userRepo.findById(id).orElseThrow(()->new NotFoundException(String.format("Table with id: %d does not exist.", id)));
//        client = userMapper.userCreateDtoToUser(userCreateDto);
        client.setNumberOfReservation(userCreateDto.getNumberOfReservation());
        userRepo.save(client);

        return userMapper.userToUserDto(client);
    }
}

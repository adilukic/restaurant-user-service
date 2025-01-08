package com.example.restaurantuserservice.service;

import com.example.restaurantuserservice.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<ClientDto> findAll(Pageable pageable);
    ClientDto getUser(Long id);
    ClientDto add(ClientCreateDto userCreateDto);
    TokenResponseDto login(TokenRequestDto tokenRequestDto);
    ClientDto updateClient(Long id, ClientCreateDto userCreateDto);

}

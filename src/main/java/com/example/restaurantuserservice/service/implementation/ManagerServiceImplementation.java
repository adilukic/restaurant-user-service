package com.example.restaurantuserservice.service.implementation;

import com.example.restaurantuserservice.domain.Manager;
import com.example.restaurantuserservice.domain.User;
import com.example.restaurantuserservice.dto.*;
import com.example.restaurantuserservice.exception.NotFoundException;
import com.example.restaurantuserservice.mapper.ManagerMapper;
import com.example.restaurantuserservice.repository.ManagerRepo;
import com.example.restaurantuserservice.security.service.TokenService;
import com.example.restaurantuserservice.service.ManagerService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ManagerServiceImplementation implements ManagerService {

    private ManagerMapper managerMapper;
    private ManagerRepo managerRepo;
    private TokenService tokenService;

    public ManagerServiceImplementation(ManagerMapper managerMapper, ManagerRepo managerRepo, TokenService tokenService) {
        this.managerMapper = managerMapper;
        this.managerRepo = managerRepo;
        this.tokenService = tokenService;
    }

    @Override
    public Page<ManagerDto> findAll(Pageable pageable) {
        return managerRepo.findAll(pageable)
                .map(managerMapper::managerToManagerDto);
    }

    @Override
    public ManagerDto add(ManagerCreateDto managerCreateDto) {

        Manager manager = managerMapper.managerCreateDtoToManager(managerCreateDto);
        managerRepo.save(manager);
        return managerMapper.managerToManagerDto(manager);

    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        Manager manager = managerRepo.findManagerByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String.format("Manager with username: %s and password: %s not found.", tokenRequestDto.getUsername(),
                        tokenRequestDto.getPassword())));
        Claims claims = Jwts.claims();
        claims.put("id", manager.getId());
        //claims.put("role", user.getRole().getName());
        return new TokenResponseDto(tokenService.generate(claims));
    }
}

package com.example.restaurantuserservice.controller;

import com.example.restaurantuserservice.dto.*;
import com.example.restaurantuserservice.security.CheckSecurity;
import com.example.restaurantuserservice.service.ManagerService;
import com.example.restaurantuserservice.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CheckSecurity
    @GetMapping("/secure-endpoint")
    public ResponseEntity<String> secureEndpoint(
            @RequestHeader(value = "Authorization", required = false) String authorization) {
        return ResponseEntity.ok("Access granted via AOP without Authentication");
    }

    @Operation(summary = "Get all users", description = "Retrieve a paginated list of all users")
    @GetMapping
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_USER", "ROLE_MANAGER"})
    public ResponseEntity<Page<ClientDto>> getAllUsers(
            @RequestHeader("Authorization") String authorization,
            @Parameter(description = "Page number", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size", example = "10") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort criteria", example = "name,asc") @RequestParam(defaultValue = "id,asc") String sort,
            Pageable pageable) {
        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
    }

    @Operation(summary = "Get user by id")
    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getUserById(@PathVariable("id")long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @Operation(summary = "Register client", description = "Register a new client")
    @PostMapping("/register/")
    public ResponseEntity<UserDto> saveClient(@RequestBody @Valid ClientCreateDto clientCreateDto) {
        return new ResponseEntity<>(userService.add(clientCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable("id") long id,@RequestBody ClientCreateDto clientCreateDto) {
        return new ResponseEntity<>(userService.updateClient(id, clientCreateDto), HttpStatus.OK);
    }

    @Operation(summary = "Login", description = "Authenticate user and return token")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginUser(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(userService.login(tokenRequestDto), HttpStatus.OK);
    }
}

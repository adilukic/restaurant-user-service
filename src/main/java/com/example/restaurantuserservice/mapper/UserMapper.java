package com.example.restaurantuserservice.mapper;

import com.example.restaurantuserservice.domain.Client;
import com.example.restaurantuserservice.domain.Manager;
import com.example.restaurantuserservice.domain.User;
import com.example.restaurantuserservice.dto.ClientDto;
import com.example.restaurantuserservice.dto.UserCreateDto;
import com.example.restaurantuserservice.dto.UserDto;
import com.example.restaurantuserservice.repository.RoleRepo;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private RoleRepo roleRepo;

    public UserMapper(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }
    public UserDto userToUserDto(User user) {


            ClientDto userDto = new ClientDto();
            userDto.setId(user.getId());
            userDto.setEmail(user.getEmail());
            userDto.setUsername(user.getUsername());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            return userDto;
        }
    public User userCreateDtoToUser(UserCreateDto userCreateDto){
        User user = new Client();
        user.setEmail(userCreateDto.getEmail());
        user.setUsername(userCreateDto.getUsername());
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        user.setPassword(userCreateDto.getPassword());
        user.setRole(roleRepo.findRoleByName("ROLE_USER").get());
        user.setDatumRodjenja(userCreateDto.getDatumRodjenja());
        return user;
    }
}

package com.example.restaurantuserservice.mapper;

import com.example.restaurantuserservice.domain.Manager;
import com.example.restaurantuserservice.dto.ManagerCreateDto;
import com.example.restaurantuserservice.dto.ManagerDto;

import com.example.restaurantuserservice.repository.RoleRepo;
import org.springframework.stereotype.Component;

@Component
public class ManagerMapper {
    private RoleRepo roleRepo;

    public ManagerMapper(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }
    public ManagerDto managerToManagerDto(Manager manager){
        ManagerDto managerDto = new ManagerDto();
        managerDto.setId(manager.getId());
        managerDto.setEmail(manager.getEmail());
        managerDto.setUsername(manager.getUsername());
        managerDto.setFirstName(manager.getFirstName());
        managerDto.setLastName(manager.getLastName());
        return managerDto;
    }
    public Manager managerCreateDtoToManager(ManagerCreateDto managerCreateDto){
        Manager manager = new Manager();
        manager.setEmail(managerCreateDto.getEmail());
        manager.setUsername(managerCreateDto.getUsername());
        manager.setFirstName(managerCreateDto.getFirstName());
        manager.setLastName(managerCreateDto.getLastName());
        manager.setPassword(managerCreateDto.getPassword());
        manager.setRole(roleRepo.findRoleByName("ROLE_MANAGER").get());
        manager.setDatumRodjenja(managerCreateDto.getDatumRodjenja());
        manager.setRestaurantName(managerCreateDto.getRestaurantName());
        manager.setDateOfReg(managerCreateDto.getDateOfReg());
        return manager;
    }
}

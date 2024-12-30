package com.example.restaurantuserservice.mapper;

import com.example.restaurantuserservice.domain.Manager;
import com.example.restaurantuserservice.dto.ManagerCreateDto;
import com.example.restaurantuserservice.dto.ManagerDto;
import com.example.restaurantuserservice.repository.ManagerRepo;
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

        return managerDto;
    }
    public Manager managerCreateDtoToManager(ManagerCreateDto managerCreateDto){
        Manager manager = new Manager();

        return manager;
    }
}

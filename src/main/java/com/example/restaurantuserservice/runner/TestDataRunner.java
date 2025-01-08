package com.example.restaurantuserservice.runner;

import com.example.restaurantuserservice.domain.*;
import com.example.restaurantuserservice.repository.RoleRepo;
import com.example.restaurantuserservice.repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {
    private RoleRepo roleRepo;
    private UserRepo userRepo;

    public TestDataRunner(RoleRepo roleRepo, UserRepo userRepo) {
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Role roleUser = new Role("ROLE_USER", "User role");
        Role roleAdmin = new Role("ROLE_ADMIN", "Admin role");
        Role roleManager = new Role("ROLE_MANAGER", "Manager role");

        roleRepo.save(roleAdmin);
        roleRepo.save(roleManager);
        roleRepo.save(roleUser);

        User admin = new Admin();
        admin.setEmail("admin@raf.rs");
        admin.setUsername("admin");
        admin.setRole(roleAdmin);
        admin.setPassword("admin");
        userRepo.save(admin);
        Client client = new Client();
        client.setRole(roleUser);
        client.setUsername("skit");
        client.setPassword("skit");
        client.setEmail("ilibasicnikola10@gmail.com");
        client.setFirstName("Nikola");
        client.setLastName("Ilibasic");
        client.setNumberOfReservation(0);
        User manager = new Manager();
        manager.setRole(roleManager);
        manager.setUsername("manager");
        manager.setPassword("manager");
        userRepo.save(manager);
        userRepo.save(client);
        User client2 = new Client();
        client2.setRole(roleUser);
        client2.setUsername("klijent2");
        client2.setPassword("sifra2");
        userRepo.save(client2);
    }
}

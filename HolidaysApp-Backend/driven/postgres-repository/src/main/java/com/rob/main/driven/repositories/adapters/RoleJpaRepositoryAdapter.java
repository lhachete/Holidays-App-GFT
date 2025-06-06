package com.rob.main.driven.repositories.adapters;

import com.rob.application.ports.driven.RoleRepositoryPort;
import com.rob.domain.models.Role;
import com.rob.main.driven.repositories.RoleMORepositoryJpaRepository;
import com.rob.main.driven.repositories.mappers.RoleMOMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.stream;

@Slf4j
@Data
@Repository
@RequiredArgsConstructor
public class RoleJpaRepositoryAdapter implements RoleRepositoryPort {

    private final RoleMORepositoryJpaRepository roleRepository;
    private final RoleMOMapper roleMOMapper;

    @Override
    public List<Role> findByNameContaining(String name) {
        log.info("Se van a buscar roles que contengan el nombre: {}", name);
        return  roleRepository.findByNameContaining(name)
                .stream()
                .map(roleMOMapper::toRole)
                .toList();
    }

    @Override
    public List<Role> findAll() {
        log.info("Se van a obtener todos los roles");
        return roleRepository.findAll()
                .stream()
                .map(roleMOMapper::toRole)
                .toList();
    }
}

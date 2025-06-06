package com.rob.driving.controllers.adapters;

import com.rob.application.ports.driving.ProjectServicePort;
import com.rob.domain.models.Project;
import com.rob.domain.models.Role;
import com.rob.driving.api.ProjectsApi;
import com.rob.driving.dtos.ProjectDTO;
import com.rob.driving.mappers.ProjectDTOMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api-rest/projects")
public class ProjectControllerAdapter implements ProjectsApi {

    private final ProjectDTOMapper projectDTOMapper;
    private final ProjectServicePort projectServicePort;


    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects(@RequestParam(name = "name", required = false) String name) {
        log.info("Se va a realizar una solicitud GET a /projects con el parámetro name: {}", name);
        List<ProjectDTO> projectDTOS = new ArrayList<>();
        List<Project> projects = projectServicePort.getProjectsByName(name);
        for (Project project : projects) {
            projectDTOS.add(projectDTOMapper.toProjectDTO(project));
        }
        log.debug("Se han encontrado {} proyectos", projectDTOS.size());
        return ResponseEntity.ok(projectDTOS);
    }
}

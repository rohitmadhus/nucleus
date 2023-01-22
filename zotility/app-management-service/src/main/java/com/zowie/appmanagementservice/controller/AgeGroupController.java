package com.zowie.appmanagementservice.controller;

import com.zowie.appmanagementservice.repository.AgeGroupRepository;
import com.zowie.datalibrary.entity.AgeGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/age/group")
public class AgeGroupController {

    @Autowired
    AgeGroupRepository ageGroupRepository;


    @GetMapping("/all")
    ResponseEntity<List<AgeGroup>> findAll(){
        List<AgeGroup> ageGroups = new ArrayList<>();
        ageGroupRepository.findAll().forEach(ageGroups::add);;
        return new ResponseEntity<>(ageGroups != null && !ageGroups.isEmpty() ? ageGroups : new ArrayList<>(), ageGroups != null && !ageGroups.isEmpty() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}

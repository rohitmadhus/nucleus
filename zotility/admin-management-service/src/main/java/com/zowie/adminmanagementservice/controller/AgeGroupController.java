package com.zowie.adminmanagementservice.controller;



import com.zowie.adminmanagementservice.repository.AgeGroupRepository;
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

    @GetMapping("/{id}")
    ResponseEntity<AgeGroup> findById(@PathVariable("id") String id){
        AgeGroup ageGroup = ageGroupRepository.findById(id);
        return new ResponseEntity<>(ageGroup != null ? ageGroup : null , ageGroup != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @PostMapping
    ResponseEntity<AgeGroup> create(@RequestBody AgeGroup ageGroup){
        AgeGroup newAgeGroup = ageGroupRepository.create(ageGroup);
        return new ResponseEntity<>(newAgeGroup != null ? newAgeGroup : null , newAgeGroup != null ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    ResponseEntity<AgeGroup> update(@RequestBody AgeGroup ageGroup){
        AgeGroup updatedAgeGroup = ageGroupRepository.update(ageGroup);
        return new ResponseEntity<>(updatedAgeGroup != null ? updatedAgeGroup : null , updatedAgeGroup != null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<AgeGroup> delete(@PathVariable("id") String id){
        AgeGroup deletedAgeGroup = ageGroupRepository.delete(id);
        return new ResponseEntity<>(deletedAgeGroup != null ? deletedAgeGroup : null , deletedAgeGroup != null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/all")
    ResponseEntity<List<AgeGroup>> findAll(){
        List<AgeGroup> ageGroups = new ArrayList<>();
        ageGroupRepository.findAll().forEach(ageGroups::add);;
        return new ResponseEntity<>(ageGroups != null && !ageGroups.isEmpty() ? ageGroups : new ArrayList<>(), ageGroups != null && !ageGroups.isEmpty() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}

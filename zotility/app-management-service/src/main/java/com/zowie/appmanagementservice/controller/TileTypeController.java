package com.zowie.appmanagementservice.controller;


import com.zowie.appmanagementservice.repository.TileTypeRepository;
import com.zowie.datalibrary.entity.TileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/tile/type")
public class TileTypeController {


    @Autowired
    TileTypeRepository tileTypeRepository;

    @GetMapping("/{id}")
    ResponseEntity<TileType> findById(@PathVariable("id") String id){
        TileType tileType = tileTypeRepository.findById(id);
        return new ResponseEntity<>(tileType != null ? tileType : null , tileType != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    ResponseEntity<List<TileType>> findAll(){
        List<TileType> tileTypes = new ArrayList<>();
        tileTypeRepository.findAll().forEach(tileTypes::add);;
        return new ResponseEntity<>(tileTypes != null && !tileTypes.isEmpty() ? tileTypes : new ArrayList<>(), tileTypes != null && !tileTypes.isEmpty() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}

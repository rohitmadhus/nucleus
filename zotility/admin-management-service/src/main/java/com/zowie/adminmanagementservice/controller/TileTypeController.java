package com.zowie.adminmanagementservice.controller;


import com.zowie.adminmanagementservice.repository.TileTypeRepository;
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

    @PostMapping
    ResponseEntity<TileType> create(@RequestBody TileType tileType){
        TileType newTileType = tileTypeRepository.create(tileType);
        return new ResponseEntity<>(newTileType != null ? newTileType : null , newTileType != null ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    ResponseEntity<TileType> update(@RequestBody TileType tileType){
        TileType updatedTileType = tileTypeRepository.update(tileType);
        return new ResponseEntity<>(updatedTileType != null ? updatedTileType : null , updatedTileType != null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<TileType> delete(@PathVariable("id") String id){
        TileType deletedTileType = tileTypeRepository.delete(id);
        return new ResponseEntity<>(deletedTileType != null ? deletedTileType : null , deletedTileType != null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/all")
    ResponseEntity<List<TileType>> findAll(){
        List<TileType> tileTypes = new ArrayList<>();
        tileTypeRepository.findAll().forEach(tileTypes::add);;
        return new ResponseEntity<>(tileTypes != null && !tileTypes.isEmpty() ? tileTypes : new ArrayList<>(), tileTypes != null && !tileTypes.isEmpty() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}

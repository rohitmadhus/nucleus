package com.zowie.adminmanagementservice.controller;


import com.zowie.adminmanagementservice.repository.TileRepository;
import com.zowie.datalibrary.entity.Tile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tile")
public class TileController {
    @Autowired
    TileRepository tileRepository;

    @GetMapping("/{id}")
    ResponseEntity<Tile> findById(@PathVariable("id") String id){
        Tile tile = tileRepository.findById(id);
        return new ResponseEntity<>(tile != null ? tile : null , tile != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @PostMapping
    ResponseEntity<Tile> create(@RequestBody Tile tile){
        Tile newTile = tileRepository.create(tile);
        return new ResponseEntity<>(newTile != null ? newTile : null , newTile != null ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    ResponseEntity<Tile> update(@RequestBody Tile tile){
        Tile updatedTile = tileRepository.update(tile);
        return new ResponseEntity<>(updatedTile != null ? updatedTile : null , updatedTile != null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Tile> delete(@PathVariable("id") String id){
        Tile newTile = tileRepository.delete(id);
        return new ResponseEntity<>(newTile != null ? newTile : null , newTile != null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/key/{key}/size/{size}")
    ResponseEntity<List<Tile>> paginated(@PathVariable("size") int size, @PathVariable("key") String key){
        List<Tile> categoryPage = tileRepository.find(key == null || key.equalsIgnoreCase("initial") ? null : key,size);
        return new ResponseEntity<>(categoryPage != null && !categoryPage.isEmpty() ? categoryPage : new ArrayList<>(), categoryPage != null && !categoryPage.isEmpty() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

}

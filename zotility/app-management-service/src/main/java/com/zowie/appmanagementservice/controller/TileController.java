package com.zowie.appmanagementservice.controller;


import com.zowie.appmanagementservice.repository.TileRepository;
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

    @GetMapping("/key/{key}/size/{size}")
    ResponseEntity<List<Tile>> paginated(@PathVariable("size") int size, @PathVariable("key") String key){
        List<Tile> categoryPage = tileRepository.find(key == null || key.equalsIgnoreCase("initial") ? null : key,size);
        return new ResponseEntity<>(categoryPage != null && !categoryPage.isEmpty() ? categoryPage : new ArrayList<>(), categoryPage != null && !categoryPage.isEmpty() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

}

package com.zowie.appmanagementservice.controller;


import com.zowie.appmanagementservice.repository.ContentTypeRepository;
import com.zowie.datalibrary.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/content/type")
public class ContentTypeController {

    @Autowired
    ContentTypeRepository contentTypeRepository;

    @GetMapping("/{id}")
    ResponseEntity<ContentType> findById(@PathVariable("id") String id){
        ContentType contentType = contentTypeRepository.findById(id);
        return new ResponseEntity<>(contentType != null ? contentType : null , contentType != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @PostMapping
    ResponseEntity<ContentType> create(@RequestBody ContentType contentType){
        ContentType newContentType = contentTypeRepository.create(contentType);
        return new ResponseEntity<>(newContentType != null ? newContentType : null , newContentType != null ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    ResponseEntity<ContentType> update(@RequestBody ContentType contentType){
        ContentType updatedContentType = contentTypeRepository.update(contentType);
        return new ResponseEntity<>(updatedContentType != null ? updatedContentType : null , updatedContentType != null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ContentType> delete(@PathVariable("id") String id){
        ContentType deletedContentType = contentTypeRepository.delete(id);
        return new ResponseEntity<>(deletedContentType != null ? deletedContentType : null , deletedContentType != null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/all")
    ResponseEntity<List<ContentType>> findAll(){
        List<ContentType> contentTypes = new ArrayList<>();
        contentTypeRepository.findAll().forEach(contentTypes::add);;
        return new ResponseEntity<>(contentTypes != null && !contentTypes.isEmpty() ? contentTypes : new ArrayList<>(), contentTypes != null && !contentTypes.isEmpty() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}

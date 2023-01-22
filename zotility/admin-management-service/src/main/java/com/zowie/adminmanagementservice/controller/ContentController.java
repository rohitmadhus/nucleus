package com.zowie.adminmanagementservice.controller;

import com.zowie.adminmanagementservice.repository.ContentRepository;
import com.zowie.adminmanagementservice.service.ContentService;
import com.zowie.datalibrary.entity.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    ContentRepository contentRepository;

    @Autowired
    ContentService contentService;

    @GetMapping("/{id}")
    ResponseEntity<Content> findById(@PathVariable("id") String id){
        Content content = contentRepository.findById(id);
        return new ResponseEntity<>(content != null ? content : null , content != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @PostMapping
    ResponseEntity<Content> create(@RequestBody Content content){
        Content newContent = contentService.create(content);
        return new ResponseEntity<>(newContent != null ? newContent : null , newContent != null ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/publish/{id}")
    ResponseEntity<Content> publish(@PathVariable("id") String id){
        Content publishedContent = contentService.publish(id);
        return new ResponseEntity<>(publishedContent != null ? publishedContent : null , publishedContent != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    ResponseEntity<Content> update(@RequestBody Content content){
        Content newContent = contentService.update(content);
        return new ResponseEntity<>(newContent != null ? newContent : null , newContent != null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Content> delete(@PathVariable("id") String id){
        Content deletedContent = contentRepository.delete(id);
        return new ResponseEntity<>(deletedContent != null ? deletedContent : null , deletedContent != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @GetMapping("index/{index}/key/{key}/size/{size}/partition/{partition}")
    ResponseEntity<List<Content>> paginated(@PathVariable("size") int size, @PathVariable("key") String key,@PathVariable("index") int index, @PathVariable("partition") String partition){
        List<Content> contentList = contentService.find(key == null || key.equalsIgnoreCase("initial") ? null : key,size,index,partition);
        return new ResponseEntity<>(contentList != null && !contentList.isEmpty() ? contentList : new ArrayList<>(), contentList != null && !contentList.isEmpty() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}

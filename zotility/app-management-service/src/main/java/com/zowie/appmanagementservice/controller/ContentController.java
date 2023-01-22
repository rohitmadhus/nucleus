package com.zowie.appmanagementservice.controller;

import com.zowie.appmanagementservice.repository.ContentRepository;
import com.zowie.appmanagementservice.service.ContentService;
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

    @GetMapping("index/{index}/key/{key}/size/{size}/partition/{partition}")
    ResponseEntity<List<Content>> paginated(@PathVariable("size") int size, @PathVariable("key") String key,@PathVariable("index") int index, @PathVariable("partition") String partition){
        List<Content> contentList = contentService.find(key == null || key.equalsIgnoreCase("initial") ? null : key,size,index,partition);
        return new ResponseEntity<>(contentList != null && !contentList.isEmpty() ? contentList : new ArrayList<>(), contentList != null && !contentList.isEmpty() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}

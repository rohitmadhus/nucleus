package com.zowie.appmanagementservice.controller;


import com.zowie.appmanagementservice.repository.CategoryRepository;
import com.zowie.datalibrary.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/{id}")
    ResponseEntity<Category> findById(@PathVariable("id") String id){
        Category category = categoryRepository.findById(id);
        return new ResponseEntity<>(category != null ? category : null , category != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @GetMapping("/key/{key}/size/{size}")
    ResponseEntity<List<Category>> paginated(@PathVariable("size") int size,@PathVariable("key") String key){
        List<Category> categoryPage = categoryRepository.find(key == null || key.equalsIgnoreCase("initial") ? null : key,size);
        return new ResponseEntity<>(categoryPage != null && !categoryPage.isEmpty() ? categoryPage : new ArrayList<>(), categoryPage != null && !categoryPage.isEmpty() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    ResponseEntity<List<Category>> findAll(){
        List<Category> contentList = new ArrayList<>();
        categoryRepository.findAll().forEach(contentList::add);;
        return new ResponseEntity<>(contentList != null && !contentList.isEmpty() ? contentList : new ArrayList<>(), contentList != null && !contentList.isEmpty() ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}

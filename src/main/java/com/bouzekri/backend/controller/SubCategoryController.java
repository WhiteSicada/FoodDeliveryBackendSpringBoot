package com.bouzekri.backend.controller;


import com.bouzekri.backend.Dto.SubCategoryDto;
import com.bouzekri.backend.model.SubCategory;
import com.bouzekri.backend.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping("/allSubCategories")
    public ResponseEntity<List<SubCategory>> show() {
        return new ResponseEntity(subCategoryService.getAllSubCategory(), HttpStatus.OK);
    }
    @PostMapping("/SubCategories/saveSubCategory")
    public ResponseEntity createRestaurant(@RequestBody SubCategoryDto subCategoryDto) {
        subCategoryService.createSubCategory(subCategoryDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}

package com.bouzekri.backend.service;

import com.bouzekri.backend.Dto.SubCategoryDto;
import com.bouzekri.backend.model.SubCategory;
import com.bouzekri.backend.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    public void createSubCategory(SubCategoryDto subCategoryDto){
        SubCategory subCategory = mapFromDtoToSubCategory(subCategoryDto);
        subCategoryRepository.save(subCategory);
    }

    private SubCategory mapFromDtoToSubCategory(SubCategoryDto subCategoryDto) {
        SubCategory subCategory = new SubCategory();
        subCategory.setName(subCategoryDto.getName());
        subCategory.setCategoryId(subCategoryDto.getCategoryId());
        return subCategory;
    }

    public List<SubCategoryDto> getAllSubCategory(){
        List<SubCategory> subCategories = subCategoryRepository.findAll();
        return subCategories.stream().map(this::mapFromSubCategoryToDto).collect(toList());
    }

    private SubCategoryDto mapFromSubCategoryToDto(SubCategory subCategory) {
        SubCategoryDto subCategoryDto = new SubCategoryDto();
        subCategoryDto.setName(subCategory.getName());
        subCategoryDto.setCategoryId(subCategory.getCategoryId());
        return subCategoryDto;
    }

}

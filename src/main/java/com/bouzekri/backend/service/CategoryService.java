package com.bouzekri.backend.service;

import com.bouzekri.backend.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void saveCategory(Category restaurant);
    List<Category> getAllCategories();
    Optional<Category> getCategoryById(Long id);
}

package com.bouzekri.backend.service;

import com.bouzekri.backend.model.Category;
import com.bouzekri.backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository restaurantRepository;


    @Override
    public void saveCategory(Category restaurant) { restaurantRepository.save(restaurant); }

    @Override
    public List<Category> getAllCategories() {
        return restaurantRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return restaurantRepository.findById(id);
    }
}
package com.bouzekri.backend.service;

import com.bouzekri.backend.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    void saveItem(Item restaurant);
    List<Item> getAllItems();
    Optional<Item> getItemById(Long id);
}

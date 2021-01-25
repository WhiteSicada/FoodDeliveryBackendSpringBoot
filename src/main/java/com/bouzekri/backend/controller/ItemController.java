package com.bouzekri.backend.controller;

import com.bouzekri.backend.model.Item;
import com.bouzekri.backend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/allItems")
    public ResponseEntity<List<Item>> show() {
        return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK);
    }
    @GetMapping("/Item/display/{id}")
    @ResponseBody
    public void showImage(@PathVariable("id") Long id, HttpServletResponse response)
            throws IOException {
        Optional<Item> item = itemService.getItemById(id);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        if (item.isPresent()){
            response.getOutputStream().write(item.get().getUrl());
            response.getOutputStream().close();
        }
    }


    @PostMapping("/Items/saveItem")
    public @ResponseBody ResponseEntity<String> createRestaurant(
            @RequestParam("categoryId") int categoryId,
            @RequestParam("subCategoryId") int subCategoryId,
            @RequestParam("name") String name,
            @RequestParam("unitPrice") double unitPrice,
            Model model,
            HttpServletRequest request,
            final @RequestParam("url") MultipartFile file) {
        try {
            CategoryController.uploadFile(request,file,model);
            byte[] imageData = file.getBytes();
            Item item = new Item();
            item.setCategoryId(categoryId);
            item.setSubCategoryId(subCategoryId);
            item.setName(name);
            item.setUnitPrice(unitPrice);
            item.setUrl(imageData);
            itemService.saveItem(item);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

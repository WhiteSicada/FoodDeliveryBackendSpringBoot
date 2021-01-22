package com.bouzekri.backend.controller;

import com.bouzekri.backend.model.Category;
import com.bouzekri.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {
    @Value("${upoadDir}")
    private String uploadFolder;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/allCategories")
    public ResponseEntity<List<Category>> show() {
        return new ResponseEntity(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/Category/display/{id}")
    @ResponseBody
    void showImage(@PathVariable("id") Long id, HttpServletResponse response)
            throws ServletException, IOException {
        Optional<Category> category = categoryService.getCategoryById(id);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(category.get().getResourceId());
        response.getOutputStream().close();
    }

    @PostMapping("/Categories/saveCategory")
    public @ResponseBody ResponseEntity<?> createRestaurant(
            @RequestParam("name") String name,
            Model model,
            HttpServletRequest request,
            final @RequestParam("resourceId") MultipartFile file) {
        try {
            String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
            String fileName = file.getOriginalFilename();
            String filePath = Paths.get(uploadDirectory, fileName).toString();
            if (fileName == null || fileName.contains("..")) {
                model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
                return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
            }
            try {
                File dir = new File(uploadDirectory);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                // Save the file locally
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                stream.write(file.getBytes());
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            byte[] imageData = file.getBytes();
            Category category = new Category();
            category.setName(name);
            category.setResourceId(imageData);
            categoryService.saveCategory(category);
            return new ResponseEntity<>("Product Saved With File - " + fileName, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

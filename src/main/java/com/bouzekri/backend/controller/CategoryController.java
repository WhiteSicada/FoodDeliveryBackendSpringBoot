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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {
    @Value("${upoadDir}")
    private static String uploadFolder;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/allCategories")
    public ResponseEntity<List<Category>> show() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/Category/display/{id}")
    @ResponseBody
    public void showImage(@PathVariable("id") Long id, HttpServletResponse response)
            throws IOException {
        Optional<Category> category = categoryService.getCategoryById(id);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        if (category.isPresent()){
            response.getOutputStream().write(category.get().getResourceId());
            response.getOutputStream().close();
        }
    }

    public static ResponseEntity<String> uploadFile(HttpServletRequest request, MultipartFile file, Model model){
        String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
        String fileName = file.getOriginalFilename();
        String filePath = Paths.get(uploadDirectory, fileName).toString();
        if (fileName == null || fileName.contains("..")) {
            model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
            return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
        }
        try {
            try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(filePath))) {
                stream.write(file.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/Categories/saveCategory")
    public @ResponseBody ResponseEntity<String> createRestaurant(
            @RequestParam("name") String name,
            Model model,
            HttpServletRequest request,
            final @RequestParam("resourceId") MultipartFile file) {
        try {
            uploadFile(request,file,model);
            byte[] imageData = file.getBytes();
            Category category = new Category();
            category.setName(name);
            category.setResourceId(imageData);
            categoryService.saveCategory(category);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

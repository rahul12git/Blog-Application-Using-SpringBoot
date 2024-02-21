package com.my.blog.controllers;

import com.my.blog.payloads.ApiResponse;
import com.my.blog.payloads.CategoryDto;
import com.my.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")

public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto cateogDto){
        CategoryDto createCategory= this.categoryService.createCategory(cateogDto);
        return  new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
    }

@PutMapping("/update/{catId}")
    public  ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryId, @PathVariable Integer catId){
        CategoryDto updateCategory = this.categoryService.updateCategory(categoryId,catId);
        return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);
}
@DeleteMapping("/deleteCategory/{catId}")
public  ResponseEntity<ApiResponse> deleteCategory( @PathVariable Integer catId) {
    this.categoryService.deleteCategory(catId);
    return new ResponseEntity<ApiResponse>(new ApiResponse("category is Deleted successfuly !!", true), HttpStatus.OK);


}
@GetMapping("/{catId}")
    public  ResponseEntity<CategoryDto> getCategory( @PathVariable Integer catId) {
       CategoryDto categoryDto= this.categoryService.getCategory(catId);
        return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);


}
@GetMapping("/getAll")
public  ResponseEntity<List<CategoryDto>> getAllCategory() {
    List<CategoryDto> categories = this.categoryService.getAllCategory();
    return  ResponseEntity.ok(categories);
}

}
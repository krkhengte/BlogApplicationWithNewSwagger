package jfs.backend.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jfs.backend.payloads.CategoryDto;
import jfs.backend.service.impl.CategoryServiceImpl;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	
	@Autowired
	private CategoryServiceImpl categoryService;
	
	// create
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		
		CategoryDto createCategory = categoryService.createCategory(categoryDto);
		
		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
		
	}
	
	// update
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId ){
		
		CategoryDto updateCategory = categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
	}
	
	// delete
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<?> deleteCategory(@PathVariable Integer categoryId){
		
		String deteleCategory = categoryService.deteleCategory(categoryId);
		return new ResponseEntity<>(deteleCategory,HttpStatus.CREATED);
	}
	
	// getSingle
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable Integer categoryId){
		
		CategoryDto category = categoryService.getCategory(categoryId);
		
		return new ResponseEntity<CategoryDto>(category,HttpStatus.CREATED);
		
	}
	
	// getAll
	@GetMapping("/")
	public ResponseEntity<java.util.List<CategoryDto>> getAllCategories() {

		java.util.List<CategoryDto> category = this.categoryService.getAllCategory();
		 return new ResponseEntity<java.util.List<CategoryDto>>(category,HttpStatus.OK);

	}
	
}

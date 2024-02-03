package jfs.backend.service;

import java.util.List;

import jfs.backend.payloads.CategoryDto;

public interface CategoryService {

	// Create

	CategoryDto createCategory(CategoryDto categoryDto);

	// Update

	CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

	// delete

	String deteleCategory(Integer categoryId);

	// get

	CategoryDto getCategory(Integer categoryId);

	// getall

	List<CategoryDto> getAllCategory();
}

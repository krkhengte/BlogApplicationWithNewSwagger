package jfs.backend.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jfs.backend.entity.Category;
import jfs.backend.exceptions.ResourceNotFoundException;
import jfs.backend.payloads.CategoryDto;
import jfs.backend.repository.CategoryRepo;
import jfs.backend.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {

		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category addedCategory = this.categoryRepo.save(category);
		return this.modelMapper.map(addedCategory, CategoryDto.class);

	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category ID", categoryId));

		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDiscription(categoryDto.getCategoryDiscription());

		Category updatedCat = this.categoryRepo.save(category);

		return this.modelMapper.map(updatedCat, CategoryDto.class);

	}

	@Override
	public String deteleCategory(Integer categoryId) {

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category ID", categoryId));

		this.categoryRepo.delete(category);
		
		return "Category Deleted";
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category ID", categoryId));

		return this.modelMapper.map(category, CategoryDto.class);

	}

	@Override
	public List<CategoryDto> getAllCategory() {

		List<Category> listCategories = categoryRepo.findAll();

		List<CategoryDto> categoryList = listCategories.stream()
				.map((cat) -> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());

		return categoryList;
	}

}

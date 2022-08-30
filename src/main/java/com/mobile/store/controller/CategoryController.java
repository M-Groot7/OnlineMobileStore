package com.mobile.store.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.store.dto.Category;
import com.mobile.store.dto.Customer;
import com.mobile.store.exception.CategoryException;
import com.mobile.store.exception.CustomerException;
import com.mobile.store.service.CategoryService;
import com.mobile.store.service.CustomerService;

@RestController
public class CategoryController {
	@Autowired
	CategoryService categoryService;

	/**
	* Method:                         -addCategory
	* Description:                    -Add category in the database
	* @param Integer                  -category id 
	* @return String                  -Success,if category add successfully,otherwise it throws CategoryException.
	* @throws CategoryException :     -It is raised due to  Category already found in the database.
	* Created By                      -Shiva Charan
	* Created Date                    -18/Aug/20
	*/
	@PostMapping("category")
	public Category addCategory(@Valid @RequestBody Category category) throws CategoryException {
		return this.categoryService.addCategory(category);
	}
	
	/**
	* Method:                         -get Category by id
	* Description:                    -get  Category by name  in the database
	* @param Integer                  -category id
	* @return String                  -Success,if we get all category by id  successfully,otherwise it throws CategoryException.
	* @throws CategoryException :     -It is raised due to  Category By Id is not found in the database.
	* Created By                      -Shiva Charan
	* Created Date						-18/Aug/2022
	*/
	@GetMapping("category/{categoryId}")
	public Optional<Category> getCategoryById(@PathVariable("categoryId") Integer categoryId) throws CategoryException {
		return this.categoryService.getCategoryById(categoryId);
	}

	/**
	* Method:                         -UpdateCategory
	* Description:                    -Update Category in the database
	* @param String                   -category Name 
	* @return string                  -Success,if category updated successfully,otherwise it throws CategoryException.
	* @throws CategoryException :     -It is raised due to  Category is not  available in the in category list 
	* Created By                      -Shiva Charan
	* Created Date						-18/Aug/2022
	*/
	@PatchMapping("category")
	public Category updateCategory(@Valid @RequestBody Category category) throws CategoryException {
		Object Category;
		return this.categoryService.updateCategory(category);
	}

	/**
	* Method:                         -DeleteCategory
	* Description:                    -Delete Category in the database
	* @param Integer                  -category id 
	* @return Boolean                 -true,if category deleted successfully,otherwise it throws CategoryException.
	* @throws CategoryException :     -It is raised due to  Category is not found in the database.
	* Created By                      -Shiva Charan
	* Created Date                    -18/Aug/2022
	*/
	@DeleteMapping("deletecategory/{categoryId}")
	public Boolean deleteCategoryById(@PathVariable("categoryId") Integer categoryId) throws CategoryException {
		return this.categoryService.deleteCategoryById(categoryId);

	}
	
	/**
	* Method:                         -get all Category
	* Description:                    -get all Category in the database
	* @param List                     -category name 
	* @return String                  -Success,if we get all category  successfully,otherwise it throws CategoryException.
	* @throws CategoryException :     -It is raised due to  Category is not found in the database.
	* Created By                      -Shiva Charan
	* Created Date						18/Aug/2022
	*/
	@GetMapping("all-categorys")
	public List<Category> getAllCategory() throws CategoryException{
		return this.categoryService.getAllCategory();
	}
	
	/**
	* Method:                         -get  Category by name
	* Description:                    -get  Category by name  in the database
	* @param List                     -category name 
	* @return String                  -Success,if we get all category by name  successfully,otherwise it throws CategoryException.
	* @throws CategoryException :     -It is raised due to  Category By Name is not found in the database.
	* Created By                      -Shiva Charan
	*/
	@GetMapping("categorybyname/{categoryName}")
	public List<Category> findCategoryByCategoryName(@PathVariable("categoryName") String categoryName) throws CustomerException, CategoryException{
		return this.categoryService.getCategoryByName(categoryName);
	}
}

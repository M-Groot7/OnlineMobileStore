package com.mobile.store.service;

import java.util.ArrayList;
import java.util.List;
import com.mobile.store.dao.WishlistRepository;
import com.mobile.store.dto.Category;
import com.mobile.store.dto.Customer;
import com.mobile.store.dto.Product;
import com.mobile.store.dto.Wishlist;
import com.mobile.store.exception.CategoryException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import com.mobile.store.dao.CartItemRepository;
import com.mobile.store.dao.CategoryRepository;
import com.mobile.store.dao.ProductRepository;
import com.mobile.store.exception.CustomerException;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ProductRepository productRepository;

	/**
	* Method:                         -addCategory
	* Description:                    -Add category in the database
	* @param Integer                  -category id 
	* @return String                  -Success,if category add successfully,otherwise it throws CategoryException.
	* @throws CategoryException :     -It is raised due to  Category already found in the database.
	* Created By                      -Shiva Charan
	* Created Date                    -18/Aug/2022
	*/
	@Override
	public Category addCategory(Category category) throws CategoryException {		
		String categoryName = category.getCategoryName();
		Optional<List<Category>> tempCategory = Optional.of(this.categoryRepository.findByCategoryNameStartsWith(categoryName));
		if (!(tempCategory.get().isEmpty())) {
			throw new CategoryException("category is already present.");
		}

		return this.categoryRepository.save(category);
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
	@Override
	public Boolean deleteCategoryById(Integer categoryId) throws CategoryException {
		Boolean result;
		Optional<Category> tempCategory = this.categoryRepository.findById(categoryId);
		if (tempCategory.isPresent()) {
			this.categoryRepository.deleteById(categoryId);
			result = true;
		} else
			result = false;
		return result;

	}

	/**
	* Method:                         -updateCategory
	* Description:                    -Update Category in the database
	* @param String                   -category Name
	* @return Boolean                 -true,if category updated successfully,otherwise it throws CategoryException.
	* @throws CategoryException :     -It is raised due to  Category is not  available in the in category list 
	* Created By                      -Shiva Charan
	* Created Date						-18/Aug/2022
	*/
	@Override
	public Category updateCategory(Category category) throws CategoryException {

		return this.categoryRepository.save(category);

	}

	/**
	* Method:                         -get all Category
	* Description:                    -get all Category in the database
	* @param string                   -category name 
	* @return String                  -Success,if we get all category  successfully,otherwise it throws CategoryException.
	* @throws CategoryException :     -It is raised due to  Category is not found in the database.
	* Created By                      -Shiva Charan
	* Created Date						-18/Aug/2022
	*/
	@Override
	public List<Category> getAllCategory() throws CategoryException {
		List<Category> getCategory = this.categoryRepository.findAll();
		if (getCategory.isEmpty())
			throw new CategoryException("category not exits");
		return this.categoryRepository.findAll();

	}

	/**
	* Method:                         -getCategoryById
	* Description:                    -get all Category by Id  in the database
	* @param Integer                  -category id
	* @return String                  -Success,if we get all category by id  successfully,otherwise it throws CategoryException.
	* @throws CategoryException :     -It is raised due to  Category By Id is not found in the database.
	* Created By                      -Shiva Charan
	* Created date						-18/Aug/2022
	*/
	@Override
	public Optional<Category> getCategoryById(Integer categoryId) throws CategoryException {
		Optional<Category> getCategory = this.categoryRepository.findById(categoryId);
		if (getCategory.isEmpty())
			throw new CategoryException("category not exits");
		return this.categoryRepository.findById(categoryId);
	}

	/**
	* Method:                         -getCategoryByName
	* Description:                    -get all Category by name  in the database
	* @param String                   -category name
	* @return String                  -Success,if we get all category by id  successfully,otherwise it throws CategoryException.
	* @throws CategoryException :     -It is raised due to  Category By Id is not found in the database.
	* Created By                      -Shiva Charan
	* Created date						-18/Aug/2022
	*/
	@Override
	public List<Category> getCategoryByName(String categoryName) {

		return (List<Category>) this.categoryRepository.findByCategoryNameStartsWith(categoryName);
	}

}
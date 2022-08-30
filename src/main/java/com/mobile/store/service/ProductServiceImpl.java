package com.mobile.store.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.mobile.store.dto.Category;
import com.mobile.store.dto.Payment;
import com.mobile.store.dto.Product;
import com.mobile.store.exception.PaymentException;
import com.mobile.store.exception.ProductException;
import com.mobile.store.dao.CategoryRepository;
import com.mobile.store.dao.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	CategoryService catigoryService;

	@Autowired
	CategoryRepository categoryRepository;

	/**
	 * Method:                         -addProduct
	 * Description:                    -Adding product in the database
	 * @param                          -Pass product details
	 * @return String                  -Success, if product added successfully, otherwise it throws ProductException
	 * @throws ProductException        -It is raised due to Product Id already present in the database
	 * Created By                      -Nishant Kumar
	 * Creation Date                   -16/Aug/2022 
	 */
	@Override
	public String addProduct(Product product) throws ProductException {
		if (product == null) {
			throw new ProductException("Payment not Added");
		}
		Optional<Product> foundProduct = this.productRepository.findById(product.getProductId());
		if (foundProduct.isPresent()) {
			throw new ProductException("Product Id already Present");
		} else {
			Product newProduct = this.productRepository.save(product);
			Integer categoryId = newProduct.getCategoryId();
			Optional<Category> category = this.categoryRepository.findById(categoryId);
			if (category.isEmpty()) {
				Integer xproductId = newProduct.getProductId();
				this.productRepository.deleteById(xproductId);
				throw new ProductException("category not found for product");
			}
			Category newCategory = category.get();
			List<Product> xProduct = newCategory.getProduct();
			xProduct.add(newProduct);
			newCategory.setProduct(xProduct);
			this.categoryRepository.save(newCategory);
		}
		return "product added successfully";
	}

	/**
	 * Method:                         -getProductById
	 * Description:                    -retrieving product for the particular productId
	 * @param                          -Pass productId
	 * @return String                  -Success, then showing the particular productId details, otherwise it throws ProductException
	 * @throws ProductException        -It is raised due to Product Id not present in the database
	 * Created By                      -Nishant Kumar
	 * Creation Date				   -16/Aug/2022 
	 */
	@Override
	public Optional<Product> getProductById(Integer productId) throws ProductException {
		// TODO Auto-generated method stub
		Optional<Product> foundProduct = this.productRepository.findById(productId);
		if (foundProduct.isEmpty()) {
			throw new ProductException("Product Id not found in record");
		}
		return foundProduct;
	}

	/**
	 * Method:                         -getAllProducts
	 * Description:                    -retrieving all products 
	 * @param                          -Pass 
	 * @return String                  -Success, then showing the all product details, otherwise it throws ProductException
	 * @throws ProductException        -It is raised due to Product List not found in the database
	 * Created By                      -Nishant Kumar
	 * Creation Date                   -16/Aug/2022 
	 */
	@Override
	public List<Product> getAllProduct() throws ProductException {
		// TODO Auto-generated method stub
		List<Product> productList = this.productRepository.findAll();
		if (productList.isEmpty()) {
			throw new ProductException("Product List not found");
		}
		return productList;
	}

	/**
	 * Method:                         -deleteProductById
	 * Description:                    -deleting product for the particular productId
	 * @param                          -Pass productId
	 * @return String           Nishant Kumar
Nishant Kumar
nallabati.charan
nallabati.charan
Bhumika Mulani
Bhumika Mulani
Prashant Lodhi
Prashant Lodhi
muhammad.saad
muhammad.saad
       -Success, then deleting the particular productId details, otherwise it throws ProductException
	 * @throws ProductException        -It is raised due to Product Id not found in the database
	 * Created By                      -Nishant Kumar
	 * Creation Date					-16/Aug/2022 
	 */
	@Override
	public Boolean deleteProductById(Integer productId) throws ProductException {
		Optional<Product> foundProduct = this.productRepository.findById(productId);
		if (foundProduct.isEmpty()) {
			throw new ProductException("Product Id not found in record");
		}
		this.productRepository.deleteById(productId);
		return true;
	}

	/**
	 * Method:                         -updateProduct
	 * Description:                    -updating product for the particular producttId
	 * @param                          -Pass product
	 * @return String                  -Success, then updating the particular productId details, otherwise it throws ProductException
	 * @throws ProductException        -It is raised due to Product Id not found in the database
	 * Created By                      -Nishant Kumar
	 * Creation Date					-16/Aug/2022
	 */
	@Override
	public Product updateProduct(Product product) throws ProductException {
		// TODO Auto-generated method stub
		return this.productRepository.save(product);
	}

	/**
	 * Method:                         -findAllProductHighToLow
	 * Description:                    -sorting product in the descending order
	 * @param                          -Pass product
	 * @return String                  -Success, then sorting the products in descending order, otherwise it throws ProductException
	 * @throws ProductException        -It is raised due to Product List not found in the database
	 * Created By                      -Nishant Kumar
	 * Creation Date					-16/Aug/2022
	 */
	@Override
	public List<Product> findAllProductHighToLow() throws ProductException {
		// TODO Auto-generated method stub
		List<Product> productList = this.productRepository.findAllByOrderByProductPriceDesc();
		if (productList.isEmpty()) {
			throw new ProductException("Product List not found");
		}
		return productList;
	}

	/**
	 * Method:                         -findAllProductLowToHigh
	 * Description:                    -sorting product in the assending order
	 * @param                          -Pass product
	 * @return String                  -Success, then sorting the products in assending order, otherwise it throws ProductException
	 * @throws ProductException        -It is raised due to Product List not found in the database
	 * Created By                      -Nishant Kumar
	 * Creation Date					-16/Aug/2022
	 */
	@Override
	public List<Product> findAllProductLowToHigh() throws ProductException {
		// TODO Auto-generated method stub
		List<Product> productList = this.productRepository.findAllByOrderByProductPriceAsc();
		if (productList.isEmpty()) {
			throw new ProductException("Product List not found");
		}
		return productList;

	}

	/**
	 * Method:                         -findProductByName
	 * Description:                    -searching product in the product list
	 * @param                          -Pass product
	 * @return String                  -Success, then searching product for the particular ProductId, otherwise it throws ProductException
	 * @throws ProductException        -It is raised due to Product Id not found in the database
	 * Created By                      -Nishant Kumar
	 * Creation Date					-16/Aug/2022
	 */
	@Override
	public List<Product> findProductByName(String productName) throws ProductException {
		// TODO Auto-generated method stub
		List<Product> foundProduct = this.productRepository.findProductByProductNameStartsWith(productName);

		if (foundProduct.isEmpty()) {
			throw new ProductException("Product Id not found in record");
		}
		return this.productRepository.findProductByProductNameStartsWith(productName);

	}

}
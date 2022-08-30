package com.mobile.store.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.store.dto.Product;
import com.mobile.store.exception.ProductException;
import com.mobile.store.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;

	/**
	 * Method                       -addProduct
	 * Description                  -add product to database
	 * @param product               -pass product details
	 * @return String               -Product added successfully
	 * Created By                   -Nishant Kumar
	 * Creation Date                -17/Aug/2022
	 */
	@PostMapping("product")
	public String addProduct(@RequestBody Product product) throws ProductException {
		return this.productService.addProduct(product);
		
	}

	/**
	 *  Method                      -getProductById
	 * Description                  -get product by Id
	 * @param productId              -pass productId
	 * @return Product              -Success, then showing the particular productId details
	 * Created By                   -Nishant Kumar
	 * Creation Date                -17/Aug/2022
	 */
	@GetMapping("product/{productId}")
	public Optional<Product> getProductById(@PathVariable("productId") Integer productId) throws ProductException {
		return this.productService.getProductById(productId);
	}

	/**
	 *  Method                      -updateProduct
	 * Description                  -update product
	 * @param product               -pass product
	 * @return Product              -Success, then updating the particular product details
	 * Created By                   -Nishant Kumar
	 * Creation Date                -17/Aug/2022
	 */
	@PatchMapping("product")
	public Product updateProduct(@RequestBody Product product) throws ProductException {
		return this.productService.updateProduct(product);
	}

	/**
	 *  Method                      -getAllProduct
	 * Description                  -get all product
	 * @param product               -pass product
	 * @return Product              -Success, then retrieve all the product details
	 * Created By                   -Nishant Kumar
	 * Creation Date                -17/Aug/2022
	 */
	@GetMapping("products/")
	public List<Product> getAllProduct() throws ProductException {
		return this.productService.getAllProduct();
	}

	/**
	 *  Method                      -deleteProductById
	 * Description                  -delete product by Id
	 * @param productId             -pass productId
	 * @return Product              -Success, then delete the product details for any particular productId
	 * Created By                   -Nishant Kumar
	 * Creation Date                -17/Aug/2022
	 */
	@DeleteMapping("product/{productId}")
	public String deleteProductById(@PathVariable("productId") Integer productId) throws ProductException {
		this.productService.deleteProductById(productId);
		return "product deleted successfully";
	}

	/**
	 *  Method                      -findingProductHighToLow
	 * Description                  -find product high to low on the basis of price
	 * @param product               -pass product
	 * @return Product              -Success, then sort the product details in descending order
	 * Created By                   -Nishant Kumar
	 * Creation Date                -17/Aug/2022
	 */
	@GetMapping("product/highToLow")
	public List<Product> findingProductHighToLow() throws ProductException {
		return this.productService.findAllProductHighToLow();
	}
	
	/**
	 *  Method                      -findingProductLowToHigh
	 * Description                  -find product high to low
	 * @param product               -pass product
	 * @return Product              -Success, then sort the product details in asscending order
	 * Created By                   -Nishant Kumar
	 * Creation Date                -17/Aug/2022
	 */
	@GetMapping("product/lowToHigh")
	public List<Product> findingProductLowToHigh() throws ProductException {
		return this.productService.findAllProductLowToHigh();
	}
	
	/**
	 *  Method                      -findProductByName
	 * Description                  -find product by name
	 * @param productName           -pass product name
	 * @return Product              -Success, then search the product for any particular product name
	 * Created By                   -Nishant Kumar
	 * Creation Date                -17/Aug/2022
	 */
	@GetMapping("products/{productName}")
	public List<Product> findProductByName(@PathVariable("productName") String productName) throws ProductException {
		return this.productService.findProductByName(productName);
	}

}

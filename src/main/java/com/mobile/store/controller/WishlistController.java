package com.mobile.store.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.store.dto.Product;
import com.mobile.store.dto.Wishlist;
import com.mobile.store.exception.CartItemException;
import com.mobile.store.exception.CustomerException;
import com.mobile.store.exception.ProductException;
import com.mobile.store.exception.WishlistException;
import com.mobile.store.service.ProductService;
import com.mobile.store.service.WishlistService;

@RestController
public class WishlistController {

	@Autowired
	WishlistService wishlistService;

    /**
    * Method:                         -addProductToWishlist
    * Description:                    -To handle a HTTP POST request
    * @param Integer				  -Customer Id
    * @return String                  -product added sucessfully
    * Created By                      -Muhammad Saad
    * Created Date                    -18/Aug/2022
    */
	@PostMapping("wishlist")
	public String addProductToWishlist(@Valid @RequestBody Wishlist wishlist)
			throws WishlistException, CustomerException, ProductException {
		return this.wishlistService.createWishlist(wishlist);

	}

    /**
    * Method:                         -getWishlistByCusId
    * Description:                    -To handle a HTTP GET request
    * @param Integer				  -Customer Id
    * @return List                    -List of products
    * Created By                      -Muhammad Saad
    * Created Date                    -18/Aug/2022
    */
	@GetMapping("wishlist/{customerId}")
	public List<Product> getWishlistByCusId(@PathVariable("customerId") Integer customerId)
			throws WishlistException, CustomerException {
		List<Wishlist> allWishlist = this.wishlistService.getWishlist(customerId);
		List<Product> products = new ArrayList<Product>();
		for (Wishlist wishlist : allWishlist) {
			products.add(wishlist.getProduct());
		}

		return products;
	}

    /**
    * Method:                         -deleteProductFromWishlist
    * Description:                    -To handle a HTTP DELETE request
    * @param Integer				  -Customer Id
    * @param Integer				  -Product Id
    * @return String                  -product delete sucessfully
    * Created By                      -Muhammad Saad
    * Created Date                    -18/Aug/2022
    */
	@DeleteMapping("wishlist/{customerId}/{productId}")
	public String deleteProductFromWishlist(@PathVariable("customerId") Integer customerId,
			@PathVariable("productId") Integer productId) throws WishlistException, CustomerException {
		this.wishlistService.removeProductFromWishlist(customerId, productId);
		return "product remove sucessfully";
	}

    /**
    * Method:                         -addProductToCartFromWishlist
    * Description:                    -To handle a HTTP PUT request
    * @param Integer				  -Customer Id
    * @param Integer				  -Product Id
    * @return String                  -product added to cart sucessfully
    * Created By                      -Muhammad Saad
    * Created Date                    -18/Aug/2022
    */
	@PutMapping("wishlist/{customerId}/{productId}")
	public String addProductToCartFromWishlist(@PathVariable("customerId") Integer customerId,
			@PathVariable("productId") Integer productId) throws WishlistException, CustomerException, CartItemException, ProductException {
		return this.wishlistService.addProductTOCartFromWishlist(customerId, productId);
	}
}
package com.whsgcashop.catalog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.whsgcashop.catalog.model.Product;

@Service
public class CatalogService {

	private static List<Product> productList = new ArrayList<Product>();
	private List<Integer> removedProductIdsList = new ArrayList<Integer>();

	static {
		productList.add(new Product(1,
				"https://raw.githubusercontent.com/GoogleCloudPlatform/microservices-demo/master/src/frontend/static/img/products/typewriter.jpg",
				"Vintage Typewriter", 67.98));
		productList.add(new Product(2,
				"https://raw.githubusercontent.com/GoogleCloudPlatform/microservices-demo/master/src/frontend/static/img/products/camera-lens.jpg",
				"Vintage Camera Lens", 12.48));
		productList.add(new Product(3,
				"https://raw.githubusercontent.com/GoogleCloudPlatform/microservices-demo/master/src/frontend/static/img/products/barista-kit.jpg",
				"Home Barista Kit", 123.99));
		productList.add(new Product(4,
				"https://raw.githubusercontent.com/GoogleCloudPlatform/microservices-demo/master/src/frontend/static/img/products/terrarium.jpg",
				"Terrarium", 36.44));
		productList.add(new Product(5,
				"https://raw.githubusercontent.com/GoogleCloudPlatform/microservices-demo/master/src/frontend/static/img/products/film-camera.jpg",
				"Film Camera", 2244.99));
		productList.add(new Product(6,
				"https://raw.githubusercontent.com/GoogleCloudPlatform/microservices-demo/master/src/frontend/static/img/products/record-player.jpg",
				"Vintage Record Player", 65.50));
		productList.add(new Product(7,
				"https://raw.githubusercontent.com/GoogleCloudPlatform/microservices-demo/master/src/frontend/static/img/products/camp-mug.jpg",
				"Metal Clamping Mug", 24.33));
		productList.add(new Product(8,
				"https://raw.githubusercontent.com/GoogleCloudPlatform/microservices-demo/master/src/frontend/static/img/products/city-bike.jpg",
				"City Bike", 789.50));
		productList.add(new Product(9,
				"https://raw.githubusercontent.com/GoogleCloudPlatform/microservices-demo/master/src/frontend/static/img/products/air-plant.jpg",
				"Air Plant", 12.29));
		productList.add(new Product(10,
				"https://images.unsplash.com/photo-1526280760714-f9e8b26f318f?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=967&q=80",
				"Clean Journal", 9.78));
	}

	public List<Product> getProducts() {
		List<Product> filteredProducts = new ArrayList<Product>();

		for (Product p : productList) {
			if (!removedProductIdsList.contains(p.getId())) {
				filteredProducts.add(p);
			}
		}

		return filteredProducts;
	}

	public Product getProductById(int productId) {
		for (int i = 0; i < productList.size(); i++) {
			Product p = productList.get(i);
			if (p.getId() == productId) {
				return productList.get(i);
			}
		}
		return null;
	}
	
	public void updateProductActiveStatus() {
		removedProductIdsList.clear();	
	}

	public void deleteProduct(int productId) {
		for (Product p : productList) {
			if (p.getId() == productId) {
				removedProductIdsList.add(p.getId());
				return;
			}
		}
	}

}
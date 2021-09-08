package com.crm.service;


import com.crm.entity.Product;
import com.crm.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsService {


    private final ProductsRepository productsRepository;

    public Product saveProduct(Product product) {
        return productsRepository.save(product);
    }

    public void deleteProductById(Long productId) {
        productsRepository.deleteById(productId);
    }

    public List<Product> getProductsInStore() {
        return productsRepository.findInStore();
    }

    public List<Product> getAllProducts() {
        return productsRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productsRepository.findById(id);
    }

    public void updateProduct(Long productId, Long amount, Long cost) {
        Product product = productsRepository.findById(productId).get();
        Product productToUpdate = new Product();
        productToUpdate.setId(product.getId());
        productToUpdate.setName(product.getName());
        productToUpdate.setAmountSold(product.getAmountSold());
        productToUpdate.setAmountWeOrdered(product.getAmountWeOrdered() + amount);
        productToUpdate.setProceeds(product.getProceeds());
        productToUpdate.setPurchaseCost(product.getPurchaseCost() + cost);
        productsRepository.save(productToUpdate);
    }

    public void updateProductAfterRequest(Product product, Long amount) {
        Product productToUpdate = new Product();
        productToUpdate.setId(product.getId());
        productToUpdate.setName(product.getName());
        productToUpdate.setAmountSold(product.getAmountSold() + amount);
        productToUpdate.setAmountWeOrdered(product.getAmountWeOrdered());
        productToUpdate.setProceeds(product.getProceeds());
        productToUpdate.setPurchaseCost(product.getPurchaseCost());
        productsRepository.save(productToUpdate);
    }

    public void updateProductAfterDone(Product product, Long amount, Long unitCost) {
        Product productToUpdate = new Product();
        productToUpdate.setId(product.getId());
        productToUpdate.setName(product.getName());
        productToUpdate.setAmountSold(product.getAmountSold());
        productToUpdate.setAmountWeOrdered(product.getAmountWeOrdered());
        productToUpdate.setProceeds(product.getProceeds() + amount * unitCost);
        productToUpdate.setPurchaseCost(product.getPurchaseCost());
        productsRepository.save(productToUpdate);
    }
}

package com.crm.controller;


import com.crm.entity.Product;
import com.crm.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ProductController {


    private final ProductsService productsService;

    @RequestMapping(value = {"/products"}, method = RequestMethod.GET)
    public String personList(Model model) {
        model.addAttribute("products", productsService.getAllProducts());
        model.addAttribute("productsInStore", productsService.getProductsInStore());
        return "products";
    }

    @RequestMapping(value = { "/products/remove" }, method = RequestMethod.POST)
    public String deleteProduct(@Valid @RequestParam Long productId) {
        productsService.deleteProductById(productId);
        return "redirect:/products";
    }

    @RequestMapping(value = {"/products/add"}, method = RequestMethod.GET)
    public String editProduct(Model model){
        return "add";
    }

    @RequestMapping(value = {"/products/add"}, method = RequestMethod.POST)
    public String addProduct(@Valid @RequestParam String name,
                             @Valid @RequestParam Long amountWeOrdered,
                             @Valid @RequestParam Long purchaseCost){
        Product addedProduct = new Product(name, 0L, amountWeOrdered, 0L,
                purchaseCost);
        productsService.saveProduct(addedProduct);
        return "redirect:/products";
    }

    @PostMapping(value = { "/products/update"})
    public String updateProduct(@Valid @RequestParam Long productId,
                                @Valid @RequestParam Long amount,
                                @Valid @RequestParam Long cost) {
        productsService.updateProduct(productId, amount, cost);
        return "redirect:/products";
    }

}

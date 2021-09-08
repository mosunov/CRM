package com.crm.controller;

import com.crm.entity.Customer;
import com.crm.entity.Order;
import com.crm.entity.Product;
import com.crm.service.CustomerService;
import com.crm.service.OrderService;
import com.crm.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ProductsService productsService;
    private final CustomerService customerService;

    @GetMapping(value = {"/orders"})
    public String personList(Model model) {

        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("products", productsService.getAllProducts());
        return "orders";
    }

    @GetMapping(value = {"/orders/request"})
    public String requestOrderList(Model model) {
        model.addAttribute("request", orderService.getAllOrdersRequest());
        model.addAttribute("products", productsService.getAllProducts());
        return "request";
    }

    @PostMapping(value = "/orders/changeToDone")
    public String changeOrderToDone(@Valid @RequestParam Long orderId,
                                    @Valid @RequestParam String notePurchase) {
        for (Order order :
                orderService.getAllOrdersById(orderId)) {
            orderService.updateOrderToDone(notePurchase, order);
            Product product = productsService.findById(order.getOrderPK().getProductId()).get();
            productsService.updateProductAfterDone(product, order.getAmount(), order.getUnitCost());
        }
        return "redirect:/orders/request";
    }

    @GetMapping(value = {"/orders/done"})
    public String doneOrderList(Model model) {
        model.addAttribute("done", orderService.getAllOrdersDone());
        return "done";
    }

    @GetMapping(value = {"orders/addOrder"})
    public String editOrder(Model model) {
        model.addAttribute("products", productsService.getAllProducts());
        model.addAttribute("customers", customerService.getAllCustomers());
        return "addOrder";
    }

    @PostMapping(value = {"orders/addOrder"})
    public String addOrder(@Valid @RequestParam Long productId,
                           @Valid @RequestParam Long amount,
                           @Valid @RequestParam Long unitCost,
                           @Valid @RequestParam String customerPhone,
                           String noteRequest) {
        Product product = productsService.findById(productId).get();
        if (product.getAmountLeft() < amount)
            return "error_order";
        Customer customer = customerService.findById(customerPhone).get();
        Long orderId = orderService.getMaxOrderId();
        Order addedOrder = new Order(orderId, product, amount, unitCost, customer, noteRequest);
        productsService.updateProductAfterRequest(product, amount);
        orderService.saveOrder(addedOrder);
        return "redirect:/orders/request";
    }

    @PostMapping(value = {"orders/addCustomer"})
    public String addCustomer(@Valid @RequestParam String customerPhone,
                              @Valid @RequestParam String customerFullName) {
        Customer customer = new Customer(customerPhone, customerFullName);
        customerService.saveCustomer(customer);
        return "redirect:/orders/addOrder";
    }

    @PostMapping(value = {"/orders/update"})
    public String updateOrder(@Valid @RequestParam Long orderId,
                              @Valid @RequestParam Long productId,
                              @Valid @RequestParam Long amount,
                              @Valid @RequestParam Long unitCost,
                              @Valid @RequestParam String customerPhone,
                              String noteRequest) {
        Product product = productsService.findById(productId).get();
        if (product.getAmountLeft() < amount)
            return "error_order";
        for (Order order :
                orderService.getAllOrdersById(orderId)) {
            if (order.getOrderPK().getProductId().equals(productId))
                return "error_same_product";
        }
        Customer customer = customerService.findById(customerPhone).get();
        Order addedOrder = new Order(orderId, product, amount, unitCost, customer, noteRequest);
        productsService.updateProductAfterRequest(product, amount);
        orderService.saveOrder(addedOrder);
        return "redirect:/orders/request";
    }
}

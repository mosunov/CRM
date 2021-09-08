package com.crm.service;

import com.crm.entity.Order;
import com.crm.entity.OrderPK;
import com.crm.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getAllOrdersRequest() {
        return orderRepository.findAllOrdersRequest();
    }

    public List<Order> getAllOrdersDone() {
        return orderRepository.findAllOrdersDone();
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrdersById(Long orderId) {
        return orderRepository.findAllOrdersById(orderId);
    }

    public void updateOrderToDone(String notePurchase, Order order) {
        Order orderToUpdate = new Order();
        OrderPK orderPK = new OrderPK(order.getOrderPK().getOrderId(), order.getOrderPK().getProductId());
        orderToUpdate.setOrderPK(orderPK);
        orderToUpdate.setProductId(order.getProductId());
        orderToUpdate.setAmount(order.getAmount());
        orderToUpdate.setUnitCost(order.getUnitCost());
        orderToUpdate.setCustomerPhone(order.getCustomerPhone());
        orderToUpdate.setRequestDate(order.getRequestDate());
        orderToUpdate.setPurchaseDate(OffsetDateTime.now());
        orderToUpdate.setNoteRequest(order.getNoteRequest());
        orderToUpdate.setNotePurchase(notePurchase);
        orderRepository.save(orderToUpdate);
    }

    public Long getMaxOrderId() {
        Long orderMaxId = 1L;
        List<Order> orderList = orderRepository.findAll();
        for (Order order :
                orderList) {
            if (order.getOrderPK().getOrderId() == null)
                orderMaxId = 1L;
            else if (order.getOrderPK().getOrderId() >= orderMaxId)
                orderMaxId = order.getOrderPK().getOrderId() + 1;
        }
        return orderMaxId;
    }
}

package com.crm.repository;

import com.crm.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAll();

    @Query(value = "SELECT o FROM Order o WHERE o.purchaseDate IS NULL")
    List<Order> findAllOrdersRequest();

    @Query(value = "SELECT o FROM Order o WHERE o.purchaseDate IS NOT NULL")
    List<Order> findAllOrdersDone();

    @Query(value = "SELECT o FROM Order o WHERE o.orderPK.orderId = :orderId")
    List<Order> findAllOrdersById(@Param("orderId") Long orderId);
}

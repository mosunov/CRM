package com.crm.repository;

import com.crm.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductsRepository extends CrudRepository<Product,Long> {
        List<Product> findAll();

        @Query(value = "SELECT o FROM Product o WHERE o.amountWeOrdered > o.amountSold")
        List<Product> findInStore();


}

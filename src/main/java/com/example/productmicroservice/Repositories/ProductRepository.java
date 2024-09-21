package com.example.productmicroservice.Repositories;

import com.example.productmicroservice.Modules.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByid(long id);
     Product save(Product product);
     Product save(long id);
     void deleteById(long id);

    Page<Product> findAll(Pageable pageable);
}

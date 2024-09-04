package com.example.productmicroservice.Repositories;

import com.example.productmicroservice.Modules.Category;
import com.example.productmicroservice.Modules.Product;
import org.hibernate.Remove;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category save(Category category);
    Optional<Category> findById(long id);
    List<Category>findAll();
    Category findByCategoryName(String categoryName);
}

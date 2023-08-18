package com.example.cameraincome.repo;

import com.example.cameraincome.model.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriesRepository extends JpaRepository<Category, Long> {
}

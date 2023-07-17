package com.example.cameraincome.repo;

import com.example.cameraincome.model.product.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReviewRepository extends JpaRepository<Review, Long> {
}

package com.example.cameraincome.service.product.review;

import com.example.cameraincome.model.product.Review;
import com.example.cameraincome.repo.IReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService implements IReviewService{

    @Autowired
    IReviewRepository iReviewRepository;

    @Override
    public Iterable<Review> findAll() {
        return iReviewRepository.findAll();
    }

    @Override
    public Optional<Review> findById(Long id) {
        return iReviewRepository.findById(id);
    }

    @Override
    public Review save(Review review) {
        return iReviewRepository.save(review);
    }

    @Override
    public void remove(Long id) {
        iReviewRepository.deleteById(id);
    }
}

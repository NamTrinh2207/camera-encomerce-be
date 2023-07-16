package com.example.cameraincome.controller;

import com.example.cameraincome.model.product.Review;
import com.example.cameraincome.service.product.review.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class ReviewController {

    @Autowired
    IReviewService reviewService;

    @GetMapping("/")
    public ResponseEntity<Iterable<Review>> showAllReview() {
        Iterable<Review> reviews = reviewService.findAll();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        return new ResponseEntity<>(reviewService.save(review), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Review> deleteReview(@PathVariable Long id) {
        Optional<Review> optionalReview = reviewService.findById(id);
        if (optionalReview.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        reviewService.remove(id);
        return new ResponseEntity<>(optionalReview.get(), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> editReview(@PathVariable Long id, @RequestBody Review review) {
        Optional<Review> optionalReview = reviewService.findById(id);
        if (optionalReview.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        review.setId(optionalReview.get().getId());
        return new ResponseEntity<>(reviewService.save(review), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReview(@PathVariable Long id) {
        Optional<Review> optionalReview = reviewService.findById(id);
        return optionalReview.map(review
                -> new ResponseEntity<>(review, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

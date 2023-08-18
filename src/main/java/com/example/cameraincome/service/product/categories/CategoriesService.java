package com.example.cameraincome.service.product.categories;

import com.example.cameraincome.model.product.Category;
import com.example.cameraincome.repo.ICategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriesService implements ICategoriesService {
    @Autowired
    private ICategoriesRepository categoriesRepository;

    @Override
    public Iterable<Category> findAll() {
        return categoriesRepository.findAll();
    }


    @Override
    public Optional<Category> findById(Long id) {
        return categoriesRepository.findById(id);
    }

    @Override
    public Category save(Category category) {
        return categoriesRepository.save(category);
    }

    @Override
    public void remove(Long id) {
        categoriesRepository.deleteById(id);
    }
}

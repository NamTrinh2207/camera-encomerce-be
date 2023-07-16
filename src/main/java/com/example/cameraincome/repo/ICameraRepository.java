package com.example.cameraincome.repo;

import com.example.cameraincome.model.product.Camera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICameraRepository extends JpaRepository<Camera, Long> {
}

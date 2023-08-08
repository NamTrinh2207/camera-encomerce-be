package com.example.cameraincome.service.product.Camera;

import com.example.cameraincome.model.product.Camera;
import com.example.cameraincome.repo.ICameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CameraService implements ICameraService{

    @Autowired
    ICameraRepository cameraRepository;

    @Override
    public Iterable<Camera> findAll() {
        return cameraRepository.findAll();
    }

    @Override
    public Optional<Camera> findById(Long id) {
        return cameraRepository.findById(id);
    }

    @Override
    public Camera save(Camera camera) {
        return cameraRepository.save(camera);
    }

    @Override
    public void remove(Long id) {
        cameraRepository.deleteById(id);
    }
}

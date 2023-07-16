package com.example.cameraincome.controller;

import com.example.cameraincome.model.product.Camera;
import com.example.cameraincome.service.product.Camera.ICameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin/camera")
public class CameraController {

    @Autowired
    ICameraService cameraService;

    @GetMapping("/")
    public ResponseEntity<Iterable<Camera>> showAllCamera() {
        Iterable<Camera> cameras = cameraService.findAll();
        return new ResponseEntity<>(cameras, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Camera> createCamera(@RequestBody Camera camera) {
        return new ResponseEntity<>(cameraService.save(camera), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Camera> deleteCamera(@PathVariable Long id) {
        Optional<Camera> camera = cameraService.findById(id);
        if (camera.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cameraService.remove(id);
        return new ResponseEntity<>(camera.get(), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Camera> editCamera(@PathVariable Long id, @RequestBody Camera camera) {
        Optional<Camera> optionalCamera = cameraService.findById(id);
        if (optionalCamera.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        camera.setId(optionalCamera.get().getId());
        return new ResponseEntity<>(cameraService.save(camera), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Camera> getCamera(@PathVariable Long id) {
        Optional<Camera> optionalCamera = cameraService.findById(id);
        return optionalCamera.map(camera
                -> new ResponseEntity<>(camera, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

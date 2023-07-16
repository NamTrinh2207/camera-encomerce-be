package com.example.cameraincome.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category")
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    private Set<Camera> cameras;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(Set<Camera> cameras) {
        this.cameras = cameras;
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(Long id, Set<Camera> cameras) {
        this.id = id;
        this.cameras = cameras;
    }

    public Category(String name, Set<Camera> cameras) {
        this.name = name;
        this.cameras = cameras;
    }

    public Category(Long id, String name, Set<Camera> cameras) {
        this.id = id;
        this.name = name;
        this.cameras = cameras;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Camera> getCameras() {
        return cameras;
    }

    public void setCameras(Set<Camera> cameras) {
        this.cameras = cameras;
    }
}
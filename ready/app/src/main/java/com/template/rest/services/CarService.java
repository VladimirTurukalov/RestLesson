package com.template.rest.services;

import com.template.rest.exceptions.ResourceNotFoundException;
import com.template.rest.models.Car;
import com.template.rest.repositories.CarRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    final
    CarRepository repository;

    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    public List<Car> getAll() {
        return repository.findAll();
    }

    public Car findById(@NonNull String id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public List<Car> findByColor(@NonNull String color) {
        return repository.findAllByColorContains(color);
    }

    public void save(@NonNull Car car) {
        repository.save(car);
    }

    public Car saveAndFlush(@NonNull Car car) {
        save(car);
        return car;
    }

    public void delete(@NonNull String id) {
        repository.deleteById(id);
    }

}

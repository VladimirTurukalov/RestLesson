package com.template.rest.controllers;

import com.template.rest.exceptions.ResourceNotFoundException;
import com.template.rest.models.Car;
import com.template.rest.pojo.RequestCar;
import com.template.rest.services.CarService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value = "/api/cars",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class CarController {

    final
    CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Car> list() {
        return service.getAll();
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Car findById(
            @PathVariable("id") String id
    ) {
        return service.findById(id);
    }

    @GetMapping("/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<Car> findByColor(
            @PathVariable("color") String color
    ) {
        return service.findByColor(color);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Car> create(
            @RequestBody RequestCar car
    ) {
        try {
            service.findById(car.getId());
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(
                    service.saveAndFlush(car.toCar()),
                    HttpStatus.CREATED
            );
        }
    }

    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Car put(
            @RequestBody RequestCar car
    ) {
        Car update = service.findById(car.getId());
        BeanUtils.copyProperties(car.toCar(), update);
        service.save(update);
        return update;
    }

    @PatchMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Car patch(
            @RequestBody Car car
    ) {
        Car update = service.findById(car.getId());
        if (car.getColor() != null) {
            update.getColor().addAll(car.getColor());
        }
        if (car.getName() != null) {
            update.setName(car.getName());
        }
        service.save(update);
        return update;
    }

    @DeleteMapping("/id/{id}")
    public void delete(
            @PathVariable("id") String id
    ) {
        service.delete(id);
    }

}

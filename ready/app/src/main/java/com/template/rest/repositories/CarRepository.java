package com.template.rest.repositories;

import com.template.rest.models.Car;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends MongoRepository<Car, String> {

    Optional<Car> findById(@NonNull String id);

    List<Car> findAllByColorContains(@NonNull String color);

}

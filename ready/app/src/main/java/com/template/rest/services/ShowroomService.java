package com.template.rest.services;

import com.template.rest.exceptions.ResourceNotFoundException;
import com.template.rest.models.Showroom;
import com.template.rest.repositories.ShowroomRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowroomService {

    final
    ShowroomRepository repository;

    public ShowroomService(ShowroomRepository repository) {
        this.repository = repository;
    }

    public List<Showroom> findAll() {
        return repository.findAll();
    }

    public Showroom findById(@NonNull String id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    private List<Showroom> findByCar(@NonNull String car) {
        return repository.findAllByCarsContains(car);
    }

}

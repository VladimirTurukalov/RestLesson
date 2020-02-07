package com.template.rest.repositories;

import com.template.rest.models.Showroom;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowroomRepository extends MongoRepository<Showroom, String> {

    Optional<Showroom> findById(@NonNull String id);

    List<Showroom> findAllByCarsContains(@NonNull String cars);

}

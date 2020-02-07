package com.template.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.rest.models.Car;
import com.template.rest.models.Showroom;
import com.template.rest.repositories.CarRepository;
import com.template.rest.repositories.ShowroomRepository;
import lombok.SneakyThrows;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class RestApplication implements CommandLineRunner {

    final
    CarRepository carRepository;

    final
    ShowroomRepository showroomRepository;

    public RestApplication(CarRepository carRepository, ShowroomRepository showroomRepository) {
        this.carRepository = carRepository;
        this.showroomRepository = showroomRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

    @Override
    @SneakyThrows
    public void run(String... args) {
        System.out.println("\n\n\nData overwriting");
        carRepository.deleteAll();
        showroomRepository.deleteAll();

        carRepository.saveAll(List.of(
                new ObjectMapper().readValue(
                        "[{\"id\":\"0\",\"name\":\"Caerham\",\"color\":[\"black\"]},{\"id\":\"1\",\"name\":\"Honda\",\"color\":[\"black\"]},{\"id\":\"2\",\"name\":\"Tesla\",\"color\":[\"red\"]},{\"id\":\"3\",\"name\":\"Dodge\",\"color\":[\"red\",\"black\"]}]",
                        Car[].class
                )
        ));
        showroomRepository.saveAll(List.of(
                new ObjectMapper().readValue(
                        "[{\"id\":\"0\",\"name\":\"Show room 1\",\"cars\":[\"0\",\"1\"]},{\"id\":\"1\",\"name\":\"Show room 2\",\"cars\":[\"2\",\"3\"]}]",
                        Showroom[].class
                )
        ));

        carRepository.findAll().forEach(System.out::println);
        showroomRepository.findAll().forEach(System.out::println);
    }
}

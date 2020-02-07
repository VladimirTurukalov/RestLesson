package com.template.rest.pojo;

import com.template.rest.models.Car;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
public class RequestCar {

    @NonNull private String id;

    @NonNull private String name;

    @NonNull private List<String> color;

    public Car toCar() {
        return new Car(id, name, color);
    }

}

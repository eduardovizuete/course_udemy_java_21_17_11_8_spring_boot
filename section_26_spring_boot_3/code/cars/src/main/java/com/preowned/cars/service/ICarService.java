package com.preowned.cars.service;

import com.preowned.cars.service.dto.CarDTO;
import com.preowned.cars.repository.entity.Car;

import java.util.List;

public interface ICarService {

    // POST
    CarDTO addCar(Car car);

    // GET
    List<CarDTO> getAllCars();

    CarDTO getCar(String regNo);

    List<CarDTO> getAllCarsByBrandName(String brandName);

    List<CarDTO> getAllCarsByCarType(String carType);

    // DELETE
    boolean deleteCar(String regNo);

    boolean deleteAllCars();

    // PUT
    boolean updateCar(String carRegNo, Car car);

}

package dat3.car.service;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    public List<CarResponse> getCars(boolean includeAll) {

        List<Car> cars = carRepository.findAll();
        List<CarResponse> response = new ArrayList<>();
        for(Car car : cars){
            CarResponse cr = new CarResponse(car, includeAll);
            response.add(cr);
        }
        // Lavet med stream i stedet for for-each
        //List<CarResponse> response = cars.stream().map((car -> new CarResponse(car, includeAll))).toList();
        //return cars.stream().map((car -> new CarResponse(car, includeAll))).toList();
        return response;
    }
    public CarResponse addCar(CarRequest body) {
        if (carRepository.existsByBrandAndModel(body.getBrand(), body.getModel())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This car already exists");
        }

        Car newCar = CarRequest.getCarEntity(body);
        newCar = carRepository.save(newCar);

        return new CarResponse(newCar, true);
    }
    public ResponseEntity<Boolean> editCar(CarRequest body, String brand, String model) {
        Car car = carRepository.findByBrandAndModel(brand, model).
                orElseThrow(()-> new
                        ResponseStatusException(HttpStatus.BAD_REQUEST,"Car with this brand and model does not exist"));
        if(!body.getBrand().equals(brand) || !body.getModel().equals(model)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot change brand or model");
        }
        car.setPricePrDay(body.getPricePrDay());
        car.setBestDiscount(body.getBestDiscount());
        carRepository.save(car);
        return ResponseEntity.ok(true);
    }
    public CarResponse findById(int id) {
        Car car = carRepository.findById(id).
                orElseThrow(()-> new
                        ResponseStatusException(HttpStatus.BAD_REQUEST,"Car with this id does not exist"));
        return new CarResponse(car, true);
    }
    public void deleteCarById(int id) {
       carRepository.deleteById(id);

    }
    public Car getCarById(int id) {
        return carRepository.findById(id).
                orElseThrow(()-> new
                        ResponseStatusException(HttpStatus.BAD_REQUEST,"Car with this id does not exist"));
    }
}

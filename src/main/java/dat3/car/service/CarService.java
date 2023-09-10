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
    public ResponseEntity<Boolean> editCar(CarRequest body, int id) {
        Car car = carRepository.findById(id).
                orElseThrow(()-> new
                        ResponseStatusException(HttpStatus.BAD_REQUEST,"Car with this id does not exist"));
        if(!(body.getId()==id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot change car");
        }
        car.setBrand(body.getBrand());
        car.setModel(body.getModel());
        car.setPricePrDay(body.getPricePrDay());
        car.setBestDiscount(body.getBestDiscount());
        carRepository.save(car);
        return ResponseEntity.ok(true);
    }
    public CarResponse findById(int id) {
        Car car = carRepository.findById(id).
                orElseThrow(()-> new
                        ResponseStatusException(HttpStatus.NOT_FOUND,"Car with this id does not exist"));
        return new CarResponse(car, true);
    }
    public void deleteCarById(int id) {
       carRepository.deleteById(id);

    }
    public Car getCarById(int id) {
        return carRepository.findById(id).
                orElseThrow(()-> new
                        ResponseStatusException(HttpStatus.NOT_FOUND,"Car with this id does not exist"));
    }
    public void setPrice(int id, double newPrice) {
        Car carToEdit = getCarIfExists(id);
        carToEdit.setPricePrDay(newPrice);
        carRepository.save(carToEdit);
    }
    public void setDiscount(int id, int newDiscount) {
        Car carToEdit = getCarIfExists(id);
        carToEdit.setBestDiscount(newDiscount);
        carRepository.save(carToEdit);
    }

    private Car getCarIfExists(int id){
        return carRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Car with this ID does not exist"));
    }
    public List<CarResponse> getCarsByBrandAndModel(String brand, String model){
        List<Car> cars = carRepository.findAllByBrandAndModel(brand, model);
        List<CarResponse> responses = cars.stream().map((car -> new CarResponse(car, false))).toList();
        return responses;
    }
    public List<CarResponse> getCarsWithHigherDiscount(int minDiscount) {
        List<Car> carsWithHigherDiscount = carRepository.findAllByBestDiscountGreaterThanEqual(minDiscount);
        List<CarResponse> carResponses = new ArrayList<>();

        for (Car car : carsWithHigherDiscount) {
            CarResponse carResponse = new CarResponse(car, false);
            carResponses.add(carResponse);
        }

        return carResponses;
    }
    public Double getAverageCarPrice() {
        return carRepository.findAverageCarPrice();
    }

}

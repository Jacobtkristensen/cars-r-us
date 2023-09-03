package dat3.car.api;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import dat3.car.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
CarService carService;
    public CarController(CarService carService) {
        this.carService = carService;
    }

    //Security ???
    @GetMapping
    List<CarResponse>getCars(){
        return carService.getCars(true);
    }
    //Security ???
    @GetMapping(path = "/{id}")
    CarResponse getCarById(@PathVariable int id) throws Exception{
        return carService.findById(id);
    }
    @PostMapping()
    CarResponse addCar(@RequestBody CarRequest body){
        return carService.addCar(body);
    }
    @PutMapping("/{id}")
    void editCar(@RequestBody CarRequest body, @PathVariable int id){
        carService.editCar(body, id);
    }
    @DeleteMapping("/{id}")
    void deleteCarById(@PathVariable int id){
        carService.deleteCarById(id);
    }
}

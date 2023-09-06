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

    //Security Admin
    @GetMapping
    List<CarResponse>getCars(){
        return carService.getCars(false);
    }

    //Security Admin
    @GetMapping(path = "/{id}")
    CarResponse getCarById(@PathVariable int id) throws Exception{
        return carService.findById(id);
    }
    //Security Anonymous
    @PostMapping()
    CarResponse addCar(@RequestBody CarRequest body){
        return carService.addCar(body);
    }
    //Security Admin
    @PutMapping("/{id}")
    void editCar(@RequestBody CarRequest body, @PathVariable int id){
        carService.editCar(body, id);
    }
    @PatchMapping("/price/{id}/{newPrice}")
    void setPrice(@PathVariable int id, @PathVariable double newPrice){
        carService.setPrice(id, newPrice);
    }
    @PatchMapping("/discount/{id}/{newDiscount}")
    void setDiscount(@PathVariable int id, @PathVariable int newDiscount){
        carService.setDiscount(id, newDiscount);
    }
    @DeleteMapping("/{id}")
    void deleteCarById(@PathVariable int id){
        carService.deleteCarById(id);
    }
}

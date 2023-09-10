package dat3.car.repository;

import dat3.car.entity.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class CarRepositoryTest {


    @Autowired
    CarRepository carRepository;

    boolean dataInitialized = false;

    @BeforeEach
    void setUp() {
        if (!dataInitialized) {
            carRepository.deleteAll();
            carRepository.save(new Car("Volvo", "V70", 1999, 15));
            carRepository.save(new Car("Volvo", "B18", 2000, 15));
            carRepository.save(new Car("VW", "Touran", 2001, 15));
            carRepository.save(new Car("Volvo", "V70", 2000, 16));
            carRepository.save(new Car("Volvo", "V70", 2001, 17));
            dataInitialized = true;
        }
    }
    //@Test
    void findAllByBrandAndModelTest () {
        List<Car> foundCars = carRepository.findAllByBrandAndModel("Volvo", "V70");

        assertEquals(3, foundCars.size());
    }
    //@Test
    void findAllByBestDiscountTest () {
        List<Car> foundCars = carRepository.findAllByBestDiscountGreaterThanEqual(17);

        assertEquals(1, foundCars.size());
    }
}
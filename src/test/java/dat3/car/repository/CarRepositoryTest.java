package dat3.car.repository;

import dat3.car.entity.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    CarRepository carRepository;

    boolean dataInitialized = false;
    @BeforeEach
    void setUp() {
        if(!dataInitialized) {
            carRepository.deleteAll();
            carRepository.save(new Car("Volvo", "V70", 1999, 15));
            carRepository.save(new Car("Volvo", "B18", 2000, 15));
            carRepository.save(new Car("VW", "Touran", 2001, 15));
            dataInitialized = true;
        }
    }
    @Test
    void countAll() {
        long count = carRepository.count();
        assertEquals(3, carRepository.findAll().size());
    }
    @Test
    void findByBrand() {
        Car car = carRepository.findByBrand("VW");
        assertEquals("VW", car.getBrand());
    }


}
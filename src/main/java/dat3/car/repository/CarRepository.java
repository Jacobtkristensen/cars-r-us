package dat3.car.repository;

import dat3.car.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> getByBrand(String brand);
}

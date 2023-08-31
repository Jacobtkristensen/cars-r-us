package dat3.car.repository;

import dat3.car.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    Car findByBrand(String brand);

    boolean existsByBrandAndModel(String brand, String model);

    Optional<Car> findByBrandAndModel(String brand, String model);
}

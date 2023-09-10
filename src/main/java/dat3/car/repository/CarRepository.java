package dat3.car.repository;

import dat3.car.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CarRepository extends JpaRepository<Car, Integer> {
    Car findByBrand(String brand);

    boolean existsByBrandAndModel(String brand, String model);

    List<Car> findAllByBrandAndModel(String brand, String model);

    List<Car> findAllByReservationsIsEmpty();

    //Custom query
    @Query("SELECT AVG(c.pricePrDay) FROM Car c")
    Double findAverageCarPrice();

    List<Car> findAllByBestDiscountGreaterThanEqual(int bestDiscount);
}

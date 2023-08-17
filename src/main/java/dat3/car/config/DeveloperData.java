package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class DeveloperData implements ApplicationRunner {
    CarRepository carRepository;

    public DeveloperData(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    void createTestData(){
        List<Car> carList = new ArrayList<>(Arrays.asList(
        Car.builder().brand("Ford").model("Mustang").pricePrDay(1500).bestDiscount(10).build(),
        Car.builder().brand("Ford").model("Mustang").pricePrDay(1500).bestDiscount(10).build(),
        Car.builder().brand("Toyota").model("Corolla").pricePrDay(1200).bestDiscount(8).build(),
        Car.builder().brand("Honda").model("Civic").pricePrDay(1300).bestDiscount(9).build(),
        Car.builder().brand("Chevrolet").model("Cruze").pricePrDay(1100).bestDiscount(7).build(),
        Car.builder().brand("Volvo").model("XC90").pricePrDay(1400).bestDiscount(8).build(),
        Car.builder().brand("Nissan").model("Altima").pricePrDay(1250).bestDiscount(6).build(),
        Car.builder().brand("BMW").model("3 Series").pricePrDay(1600).bestDiscount(12).build(),
        Car.builder().brand("Mercedes-Benz").model("C-Class").pricePrDay(1450).bestDiscount(11).build(),
        Car.builder().brand("Audi").model("A4").pricePrDay(1550).bestDiscount(10).build(),
        Car.builder().brand("Volkswagen").model("Golf").pricePrDay(1100).bestDiscount(5).build(),
        Car.builder().brand("Kia").model("Sorento").pricePrDay(1350).bestDiscount(7).build(),
        Car.builder().brand("Subaru").model("Outback").pricePrDay(1300).bestDiscount(6).build(),
        Car.builder().brand("Mazda").model("CX-5").pricePrDay(1250).bestDiscount(5).build(),
        Car.builder().brand("Jeep").model("Wrangler").pricePrDay(1550).bestDiscount(9).build(),
        Car.builder().brand("Lexus").model("RX").pricePrDay(1700).bestDiscount(10).build()
        ));
        carRepository.saveAll(carList);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createTestData();
        System.out.println("Allegdedly created test data xD");
    }
}
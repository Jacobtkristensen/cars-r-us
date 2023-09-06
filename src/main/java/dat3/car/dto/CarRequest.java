package dat3.car.dto;

import dat3.car.entity.Car;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarRequest {
    private int id;
    private String brand;
    private String model;
    private double pricePrDay;
    private int bestDiscount;
    public static Car getCarEntity(CarRequest c){
        return new Car(c.getBrand(),c.getModel(), c.getPricePrDay(),c.getBestDiscount());
    }

    // Car to CarRequest conversion
    public CarRequest(Car c){
        this.brand = c.getBrand();
        this.model = c.getModel();
        this.pricePrDay = c.getPricePrDay();
        this.bestDiscount = c.getBestDiscount();
    }
    }


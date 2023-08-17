package dat3.car.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "car_brand", nullable = false, length = 50)
    String brand;

    @Column(name = "car_model", nullable = false, length = 50)
    String model;

    @Column(name = "rental_price_day", nullable = false)
    double pricePrDay;

    @Column(name = "max_discount", nullable = false)
    int bestDiscount;

}
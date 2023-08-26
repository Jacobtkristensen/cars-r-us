package dat3.car.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "car_brand", nullable = false, length = 50)
    private String brand;

    @Column(name = "car_model", nullable = false, length = 50)
    private String model;

    @Column(name = "rental_price_day", nullable = false)
    private double pricePrDay;

    @Column(name = "max_discount", nullable = false)
    private int bestDiscount;

    @UpdateTimestamp
    @Column(name = "last_edited")
    private LocalDateTime lastEdited;

    @CreationTimestamp
    @Column(name = "created")
    private LocalDateTime created;
}
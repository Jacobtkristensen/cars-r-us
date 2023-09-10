package dat3.car.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car extends AdminDetails {

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

    @OneToMany(mappedBy = "car")
    private List<Reservation> reservations;

    public void addReservation(Reservation reservation) {
        if (reservations == null) {
            reservations = new ArrayList<>();
        }
            reservations.add(reservation);
    }
    public Car(String brand, String model, double pricePrDay, int bestDiscount) {
        this.brand = brand;
        this.model = model;
        this.pricePrDay = pricePrDay;
        this.bestDiscount = bestDiscount;
    }
}
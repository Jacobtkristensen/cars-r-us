package dat3.car.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
//lombok
@Entity
public class Reservation extends AdminDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    LocalDate rentalDate;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Member member;



    public Reservation( LocalDate rentalDate, Car car, Member member) {
        this.car = car;
        this.member = member;
        this.rentalDate = rentalDate;
        car.addReservation(this);
        member.addReservation(this);
    }
}

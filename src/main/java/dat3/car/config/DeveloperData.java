package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class DeveloperData implements ApplicationRunner {
    CarRepository carRepository;
    MemberRepository memberRepository;
    public DeveloperData(CarRepository carRepository, MemberRepository memberRepository){
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("Ford", "Mustang", 1500, 10));
        carList.add(new Car("Ford", "Mustang", 1300, 12));
        carList.add(new Car("Toyota", "Corolla", 1200, 8));
        carList.add(new Car("Honda", "Civic", 1300, 9));
        carList.add(new Car("Chevrolet", "Cruze", 1100, 7));
        carList.add(new Car("Volvo", "XC90", 1400, 8));
        carList.add(new Car("Nissan", "Altima", 1250, 6));
        carList.add(new Car("BMW", "3 Series", 1600, 12));
        carList.add(new Car("Mercedes-Benz", "C-Class", 1450, 11));
        carList.add(new Car("Audi", "A4", 1550, 10));
        carList.add(new Car("Volkswagen", "Golf", 1100, 5));
        carList.add(new Car("Kia", "Sorento", 1350, 7));
        carList.add(new Car("Subaru", "Outback", 1300, 6));
        carList.add(new Car("Mazda", "CX-5", 1250, 5));
        carList.add(new Car("Jeep", "Wrangler", 1550, 9));
        carList.add(new Car("Lexus", "RX", 1700, 10));

        carRepository.saveAll(carList);
        List<Member> memberList = new ArrayList<>();
        memberList.add(new Member("user1", "password1", "user1@example.com",
                "John", "Doe", "123 Main St", "Cityville", "12345"));
        memberList.add(new Member("user2", "password2", "user2@example.com",
                "Jane", "Smith", "456 Elm St", "Townsville", "56789"));
        memberList.add(new Member("user3", "password3", "user3@example.com",
                "Alice", "Johnson", "789 Oak St", "Villagetown", "98765"));
        memberList.add(new Member("user4", "password4", "user4@example.com",
                "Bob", "Williams", "101 Pine St", "Suburbia", "54321"));
        memberList.add(new Member("user5", "password5", "user5@example.com",
                "Eve", "Brown", "111 Cedar St", "Countryside", "11111"));


        memberRepository.saveAll(memberList);

        System.out.println("Allegdedly created test data");
    }
}
package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Configuration
public class DeveloperData implements ApplicationRunner {
    CarRepository carRepository;
    MemberRepository memberRepository;
    ReservationRepository reservationRepository;
    public DeveloperData(CarRepository carRepository, MemberRepository memberRepository, ReservationRepository reservationRepository){
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
        this.reservationRepository = reservationRepository;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("Ford", "Mustang", 1300, 12));
        carList.add(new Car("Toyota", "Corolla", 1200, 8));
        carList.add(new Car("Honda", "Civic", 1300, 9));
        carList.add(new Car("Chevrolet", "Cruze", 1100, 7));
        carList.add(new Car("Volvo", "V70", 1400, 8));
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
        carList.add(new Car("Volvo", "V70", 1100, 5));
        carList.add(new Car("Volvo", "V70", 1900, 8));


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

        Car car1 = new Car("VW", "Golf", 760, 25);
        Member m1 = new Member("Jan","test12","a@b.dk","Jan","Jensen","Lyngbyvej 1","Lyngby","2800");
        carRepository.save(car1);
        memberRepository.save(m1);

        LocalDate date1 = LocalDate.of(2023,12,12);
        LocalDate date2 = date1.plusDays(2);
        Reservation r1 = new Reservation(date1, car1, m1);
        Reservation r2 = new Reservation(date2, car1, m1);
        reservationRepository.save(r1);
        reservationRepository.save(r2);

        System.out.println("xxxx ------> "+car1.getReservations().size());
        System.out.println("xxxx ------> "+m1.getReservations().size());

        System.out.println("Should find: "+reservationRepository.existsByCarIdAndRentalDate(car1.getId(), date1));
        System.out.println("Should NOT find: "+reservationRepository.existsByCarIdAndRentalDate(car1.getId(), date1.plusDays(5)));

        setupUserWithRoleUsers();
    }
    @Autowired
    UserWithRolesRepository userWithRolesRepository;

    final String passwordUsedByAll = "test12";

    /*****************************************************************************************
     NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
     iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
     *****************************************************************************************/
    private void setupUserWithRoleUsers() {

        System.out.println("******************************************************************************");
        System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
        System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
        System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
        System.out.println("******************************************************************************");
        UserWithRoles user1 = new UserWithRoles("user20", passwordUsedByAll, "user1@a.dk");
        UserWithRoles user2 = new UserWithRoles("user21", passwordUsedByAll, "user2@a.dk");
        UserWithRoles user3 = new UserWithRoles("user22", passwordUsedByAll, "user3@a.dk");
        UserWithRoles user4 = new UserWithRoles("user23", passwordUsedByAll, "user4@a.dk");
        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user2.addRole(Role.USER);
        user3.addRole(Role.ADMIN);
//        No Role assigned to user4
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
        userWithRolesRepository.save(user4);
    }
}
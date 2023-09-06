package dat3.car.repository;

import dat3.car.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MemberRepository extends JpaRepository<Member, String>{

    Member findByUsername(String username);
}

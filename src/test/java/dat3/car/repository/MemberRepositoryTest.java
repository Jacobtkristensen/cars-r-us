package dat3.car.repository;


import dat3.car.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    boolean dataInitialized = false;
    @BeforeEach
    void setUp() {
        if(!dataInitialized) {
            memberRepository.deleteAll();
            memberRepository.save(new Member("usertest1", "password1", "usertest1@example.com",
                            "AliceTest", "JohnsonTest", "789 Oak St Test", "VillagetownTest", "98765Test"));
            memberRepository.save(new Member("usertest2", "password2", "usertest2@example.com",
                            "BobTest", "SmithTest", "456 Elm St Test", "VillagetownTest", "12345Test"));
            dataInitialized = true;
        }
    }
    @Test
    void countAll() {
        long count = memberRepository.count();
        assertEquals(2, memberRepository.findAll().size());
    }
    @Test
    void findByUsername() {
        Member member = memberRepository.findByUsername("usertest1");
        assertEquals("usertest1", member.getUsername());
    }
}
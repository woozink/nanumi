package com.ssafy.nanumi.db.repository;

import com.ssafy.nanumi.db.entity.Address;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
@Testcontainers
@DataJpaTest // 내부적으로 메서드 끝날 때마다 롤백. (@Transactional)
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @DisplayName("Address 레코드를 삽입한다.")
    @Test
    void insert() {
        Address address = Address.builder()
                .si("si")
                .dong("dong")
                .guGun("guGun")
                .build();

        Address createdAddress = addressRepository.save(address);
        Address foundAddress = addressRepository.findById(createdAddress.getId()).get();

        assertThat(foundAddress).isEqualTo(createdAddress);
    }
}
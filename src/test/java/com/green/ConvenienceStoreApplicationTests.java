package com.green;

import com.green.domain.entity.*;
import com.green.security.MyRole;
import com.green.utils.MyFileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootTest
class ConvenienceStoreApplicationTests {

    @Autowired
    private PasswordEncoder pe;

    @Value("${file.location.temp}")
    private String locationTemp;

    @Value("${file.location.upload}")
    private String locationUpload;

    @Autowired
    ImagesEntityRepository imgRepo;

    @Autowired
    EmployeesEntityRepository empRepo;

    @Autowired
    AddressEntityRepository addrRepo;
   @Test
    void 관리자생성테스트() {


        //사원엔티티 저장
        empRepo.save(EmployeesEntity.builder()
                .email("admin@CUAgain.com")
                .pass(pe.encode("1234"))
                .name("admin")
                .hireDate(LocalDate.parse("2023-01-09"))
                .phone("010-1234-5678")
                .role(MyRole.ADMIN)
                .build());

    }

}

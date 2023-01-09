package com.green.service.impl;

import com.green.domain.dto.EmployeesInsertDTO;
import com.green.domain.dto.EmployeesListDTO;
import com.green.domain.entity.*;
import com.green.security.MyRole;
import com.green.service.EmployeesService;
import com.green.utils.MyFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeesServiceProcess  implements EmployeesService {

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


    //사원리스트페이지에 들어갈 정보 담아주기
    @Override
    public void getEmpList(Model model) {


        empRepo.findAll().stream().map(EmployeesListDTO::new).collect(Collectors.toList());
    }

    @Override
    public Map<String, String> fileTempUpload(MultipartFile gimg) {
        return MyFileUtils.fileUpload(gimg, locationTemp);
    }

    /**
     * 사원 등록 처리(이미지, 배송지,사원엔티티)
     */
    @Override
    public void save(EmployeesInsertDTO dto) {
        //이미지파일 이동
        //temp 폴더 상위폴더인 upload로 이동
        String[] newName={dto.getNewName()};
        MyFileUtils.moveUploadLocationFromTemp(newName,locationUpload);
        //이미지파일 저장,
        ImagesEntity img = imgRepo.save(ImagesEntity.builder()
                        .orgName(dto.getOrgName())
                        .newName(dto.getNewName())
                        .url(locationUpload)
                .build());
        //배송지 정보 저장
        AddressEntity address = addrRepo.save(AddressEntity.builder()
                .postcode(dto.getPostcode())
                .roadAddress(dto.getRoadAddress())
                .jibunAddress(dto.getJibunAddress())
                .detailAddress(dto.getDetailAddress())
                .extraAddress(dto.getExtraAddress())
                .build());
        //사원엔티티 저장
        empRepo.save(EmployeesEntity.builder()
                        .email(dto.getEmail()+"@CUAgain.com")
                        .pass(pe.encode(dto.getPass()))
                        .name(dto.getName())
                        .hireDate(LocalDate.parse(dto.getHireDate()))
                        .phone(dto.getPhone())
                        .image(img)
                        .position(Position.valueOf(dto.getPosition()))
                        .role(MyRole.USER)
                        .address(address)
                .build());

    }
}

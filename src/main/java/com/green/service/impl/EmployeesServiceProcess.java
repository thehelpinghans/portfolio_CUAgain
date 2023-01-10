package com.green.service.impl;

import com.green.domain.dto.EmployeesDetailDTO;
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

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeesServiceProcess implements EmployeesService {

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

    @Autowired
    DepartmentEntityRepository depaRepo;

    @Autowired
    TeamEntityRepository teamRepo;



    //부서이름받아서 팀리스트 반환
    @Override
    public List<String> getTeamListOfDef(String depName) {
        long depId = depaRepo.findByName(depName).orElseThrow().getId();
        return teamRepo.findByDepId(depId).stream().map(TeamEntity::getName).collect(Collectors.toList());
    }

    //등록시 필요한 부서, 팀, 직책정보 가져오기
    @Override
    public void getBaseInfo(Model model) {
        //직책정보
        List<Position> positionList = List.of(Position.values());
        model.addAttribute("positionList", positionList);

        //부서정보
        List<String> depList = depaRepo.findAll().stream().map(DepartmentEntity::getName).collect(Collectors.toList());
        model.addAttribute("depList", depList);
        /*
        //팀정보
        List<String> teamList = teamRepo.findAll().stream().map(TeamEntity::getName).collect(Collectors.toList());
        model.addAttribute("teamList", teamList);
*/
    }

    //사원 디테일 태그에 값 가져가기
    @Override
    public void getDetail(long memberId, Model model) {
        EmployeesDetailDTO dto = empRepo.findById(memberId).map(EmployeesDetailDTO::new).get();
        model.addAttribute("dto", dto );
    }

    //사원리스트페이지에 들어갈 정보 담아주기
    @Override
    public void getEmpList(Model model) {

        List<EmployeesListDTO> list = empRepo.findAll().stream()
                .map(e-> new EmployeesListDTO(e).address(e.getAddress()))
                .collect(Collectors.toList());
        model.addAttribute("list",list );
    }

    @Override
    public Map<String, String> fileTempUpload(MultipartFile gimg) {
        return MyFileUtils.fileUpload(gimg, locationTemp);
    }

    /**
     * 사원 등록 처리(이미지, 배송지,사원엔티티)
     */
    @Transactional
    @Override
    public void save(EmployeesInsertDTO dto) {
        //이미지파일 이동
        //temp 폴더 상위폴더인 upload로 이동
        String[] newName = {dto.getNewName()};
        MyFileUtils.moveUploadLocationFromTemp(newName, locationUpload);
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
                .email(dto.getEmail() + "@CUAgain.com")
                .pass(pe.encode(dto.getPass()))
                .name(dto.getName())
                .hireDate(LocalDate.parse(dto.getHireDate()))
                .phone(dto.getPhone())
                .image(img)
                .team(teamRepo.findByName(dto.getTeam()).orElseThrow())
                .department(depaRepo.findByName(dto.getDepartment()).orElseThrow())
                .position(Position.valueOf(dto.getPosition()))
                .address(address)
                .build().addRole(MyRole.USER).addRole(MyRole.ADMIN)//일단임시로롤다줌
        );

    }
}

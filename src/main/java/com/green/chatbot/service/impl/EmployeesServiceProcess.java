package com.green.chatbot.service.impl;

import com.green.domain.dto.EmployeesDetailDTO;
import com.green.domain.dto.EmployeesInsertDTO;
import com.green.domain.dto.EmployeesListDTO;
import com.green.domain.entity.*;
import com.green.security.MyRole;
import com.green.chatbot.service.EmployeesService;
import com.green.utils.MyFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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


    /**
     *
     * @param type  성명(name), 부서명(dep), 사원번호(id) 값 중 하나
     * @param data  검색어
     * @param model 리스트 담아갈 모델
     */
    @Override
    public void getEmpListBySearch(String type, String data, Model model) {
        if(type.equals("name")){
            List<EmployeesListDTO> list = empRepo.findByNameContaining(data).stream()
                    .map(e-> new EmployeesListDTO(e).address(e.getAddress()))
                    .collect(Collectors.toList());
            model.addAttribute("list",list );
        } else if (type.equals("dep")) {
            List<EmployeesListDTO> list = empRepo.findByDepNameContaining(data).stream()
                    .map(e-> new EmployeesListDTO(e).address(e.getAddress()))
                    .collect(Collectors.toList());
            model.addAttribute("list",list );
        } else{     //type.equals("id")
            model.addAttribute("list",
                    empRepo.findById(Long.valueOf(data))
                            .map(e-> new EmployeesListDTO(e).address(e.getAddress()))
                            .orElseThrow());
        }
    }

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
        List<String> teamList = teamRepo.findByDepId().stream().map(TeamEntity::getName).collect(Collectors.toList());
        model.addAttribute("selectedTeamList", teamList);
        */
    }

    //사원 디테일 태그에 값 가져가기
    @Override
    public void getDetail(long memberId, Model model) {

        long defId = empRepo.findById(memberId).orElseThrow().getDep().getId();
        EmployeesDetailDTO dto = empRepo.findById(memberId).map(EmployeesDetailDTO::new).get();

        //직책정보
        List<Position> positionList = List.of(Position.values());
        model.addAttribute("positionList", positionList);

        //부서정보
        List<String> depList = depaRepo.findAll().stream().map(DepartmentEntity::getName).collect(Collectors.toList());
        model.addAttribute("depList", depList);

        //팀정보
        List<String> teamList = teamRepo.findByDepId(defId).stream().map(TeamEntity::getName).collect(Collectors.toList());
        model.addAttribute("selectedTeamList", teamList);

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
        Position position = Position.valueOf(dto.getPosition());
        EmployeesEntity entity = EmployeesEntity.builder()
                .email(dto.getEmail() + "@CUAgain.com")
                .pass(pe.encode(dto.getPass()))
                .name(dto.getName())
                .hireDate(LocalDate.parse(dto.getHireDate()))
                .phone(dto.getPhone())
                .image(img)
                .team(teamRepo.findByName(dto.getTeam()).orElseThrow())
                .dep(depaRepo.findByName(dto.getDepartment()).orElseThrow())
                .position(position)
                .address(address)
                .build().addRole(MyRole.USER);
        if(dto.getPosition().equals("사장")||dto.getPosition().equals("이사")||
                dto.getPosition().equals("부장")||dto.getPosition().equals("팀장")||
                dto.getPosition().equals("대리")){
            entity.addRole(MyRole.ADMIN);
        }
        empRepo.save(entity);//대리 이상이면 관리자 Role 부여
    }

    //사원 비밀번호 수정 처리
    @Transactional
    @Override
    public void passUpdate(long empId, String pass) {
        EmployeesEntity entity = empRepo.findById(empId).orElseThrow();
        entity.updatePass(pe.encode(pass));
    }

    //사원 정보수정 처리
    @Transactional
    @Override
    public void update(EmployeesDetailDTO dto) {

        //사원엔티티 기본정보 수정
        //dto.setPass(pe.encode(dto.getPass()));
        EmployeesEntity entity = empRepo.findById(dto.getId()).orElseThrow().update(dto);

        //이미지파일 이동
        //temp 폴더 상위폴더인 upload로 이동
        String[] newName = {dto.getNewName()};
        Optional<ImagesEntity> optImg = imgRepo.findByNewName(newName[0]);
        ImagesEntity img;
        if(optImg.isEmpty()){
            MyFileUtils.moveUploadLocationFromTemp(newName, locationUpload);
            //이미지파일 저장,
            img= imgRepo.save(ImagesEntity.builder()
                    .orgName(dto.getOrgName())
                    .newName(dto.getNewName())
                    .url(locationUpload)
                    .build());
        }else{
            img=optImg.get();
        }
        TeamEntity team = teamRepo.findByName(dto.getTeam()).orElseThrow();
        DepartmentEntity dep = depaRepo.findByName(dto.getDepartment()).orElseThrow();
        AddressEntity address = entity.getAddress();
        address.update(dto);

        //사원엔티티 엮여있는거 수정
        entity.additionalUpdate(img, team, dep, address);
        /*
        this.end = end;
        this.endDate = endDate;
        */

    }
}

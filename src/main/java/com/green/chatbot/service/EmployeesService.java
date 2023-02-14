package com.green.chatbot.service;

import com.green.domain.dto.EmployeesDetailDTO;
import com.green.domain.dto.EmployeesInsertDTO;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface EmployeesService {
    Map<String, String> fileTempUpload(MultipartFile gimg);

    void save(EmployeesInsertDTO dto);

    void getEmpList(Model model);

    void getDetail(long memberId, Model model);

    void getBaseInfo(Model model);

    List<String> getTeamListOfDef(String depName);

    void getEmpListBySearch(String type, String data, Model model);

    void update(EmployeesDetailDTO dto);

    void passUpdate(long empId, String pass);
}

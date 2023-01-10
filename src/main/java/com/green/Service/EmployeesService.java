package com.green.Service;

import com.green.domain.dto.EmployeesInsertDTO;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface EmployeesService {
    Map<String, String> fileTempUpload(MultipartFile gimg);

    void save(EmployeesInsertDTO dto);

    void getEmpList(Model model);
}

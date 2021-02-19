package hu.kriszprog.cvcreatorbackend.controller;

import hu.kriszprog.cvcreatorbackend.entity.CVTitle;
import hu.kriszprog.cvcreatorbackend.service.CVProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cv")
public class CVController {

    @Autowired
    private CVProvider cvProvider;

    @GetMapping("/all")
    public List<CVTitle> getAllCV() {
        return cvProvider.getAllCV();
    }
}

package hu.kriszprog.cvcreatorbackend.controller;

import hu.kriszprog.cvcreatorbackend.model.CVModel;
import hu.kriszprog.cvcreatorbackend.model.CVIdentifiersModel;
import hu.kriszprog.cvcreatorbackend.service.CVProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cv")
public class CVController {

    @Autowired
    private CVProvider cvProvider;

    @GetMapping("/all")
    public List<CVIdentifiersModel> getAllCVIdentifiers() {
        return cvProvider.getAllCVIdentifiers();
    }

    @GetMapping("/{cv_id}")
    public CVModel getCVById(@PathVariable("cv_id") Long id) {
        return cvProvider.getCVById(id);
    }

    @PostMapping("/add")
    public CVIdentifiersModel addNewCVTitle(@RequestBody CVIdentifiersModel cvIdentifiersModel) {
        return cvProvider.addNewCV(cvIdentifiersModel);
    }
}

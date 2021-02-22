package hu.kriszprog.cvcreatorbackend.controller;

import hu.kriszprog.cvcreatorbackend.model.CVTitleModel;
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
    public List<CVTitleModel> getAllCV() {
        return cvProvider.getAllCV();
    }

    @GetMapping("/{cv_id}")
    public CVTitleModel getCVById(@PathVariable("cv_id") Long id) {
        return cvProvider.getCVById(id);
    }

    @PostMapping("/add")
    public CVTitleModel addNewCVTitle(@RequestBody CVTitleModel cvTitleModel) {
        CVTitleModel model = cvProvider.addNewCV(cvTitleModel);
        System.out.println(model);
        return model;
    }
}

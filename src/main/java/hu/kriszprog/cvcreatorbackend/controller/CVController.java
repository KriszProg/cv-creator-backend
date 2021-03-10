package hu.kriszprog.cvcreatorbackend.controller;

import hu.kriszprog.cvcreatorbackend.entity.*;
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

    @PostMapping("/{cv_id}/update/title-and-candidate")
    public String updateTitleAndCandidateInCV(@PathVariable("cv_id") Long id, @RequestBody CV cv) {
        cvProvider.updateTitleAndCandidateInCV(id, cv.getTitle(), cv.getCandidate());
        return ("ok");
    }

    @GetMapping("/candidate")
    public List<Candidate> getAllCandidates() {
        return cvProvider.getAllCandidate();
    }

    @PostMapping("/{cv_id}/update/image")
    public String updateImageInCV(@PathVariable("cv_id") Long id, @RequestBody Image image) {
        cvProvider.updateImageInCV(id, image);
        return ("ok");
    }

    @PostMapping("/{cv_id}/update/contact")
    public String updateContactInCV(@PathVariable("cv_id") Long id, @RequestBody Contact contact) {
        cvProvider.updateContactInCV(id, contact);
        return ("ok");
    }

    @PostMapping("/{cv_id}/update/personal-info")
    public String updatePersonalInfoInCV(@PathVariable("cv_id") Long id, @RequestBody PersonalInfo personalInfo) {
        cvProvider.updatePersonalInfoInCV(id, personalInfo);
        return ("ok");
    }

    @PostMapping("/{cv_id}/update/projects")
    public String updateProjectsInCV(@PathVariable("cv_id") Long id, @RequestBody List<Project> projectList) {
        cvProvider.updateProjectsInCV(id, projectList);
        return ("ok");
    }

    @PostMapping("/{cv_id}/update/jobs")
    public String updateJobsInCV(@PathVariable("cv_id") Long id, @RequestBody List<Job> jobList) {
        cvProvider.updateJobsInCV(id, jobList);
        return ("ok");
    }

    @PostMapping("/{cv_id}/update/qualifications")
    public String updateQualificationsInCV(@PathVariable("cv_id") Long id, @RequestBody List<Qualification> qualificationList) {
        cvProvider.updateQualificationsInCV(id, qualificationList);
        return ("ok");
    }
}

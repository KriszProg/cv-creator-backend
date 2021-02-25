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

    @GetMapping("/candidate")
    public List<Candidate> getAllCandidates() {
        return cvProvider.getAllCandidate();
    }
    //TODO: Is POST appropriate or should I use another Mapping?
    @PostMapping("/{cv_id}/add-candidate")
    public String updateCandidateInCV(@PathVariable("cv_id") Long id, @RequestBody Candidate candidate) {
        cvProvider.updateCandidateInCV(id, candidate);
        return ("ok");
    }

    @PostMapping("/{cv_id}/update/self-definition")
    public String updateSelfDefinitionInCV(@PathVariable("cv_id") Long id, @RequestBody SelfDefinition selfDefinition) {
        cvProvider.updateSelfDefinitionInCV(id, selfDefinition);
        return ("ok");
    }

    @PostMapping("/{cv_id}/update/strength")
    public String updateStrengthInCV(@PathVariable("cv_id") Long id, @RequestBody Strength strength) {
        cvProvider.updateStrengthInCV(id, strength);
        return ("ok");
    }

    @PostMapping("/{cv_id}/update/mentor-opinion")
    public String updateMentorOpinionInCV(@PathVariable("cv_id") Long id, @RequestBody MentorOpinion mentorOpinion) {
        cvProvider.updateMentorOpinionInCV(id, mentorOpinion);
        return ("ok");
    }

    @PostMapping("/{cv_id}/update/contact")
    public String updateContactInCV(@PathVariable("cv_id") Long id, @RequestBody Contact contact) {
        cvProvider.updateContactInCV(id, contact);
        return ("ok");
    }
}

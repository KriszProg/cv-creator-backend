package hu.kriszprog.cvcreatorbackend.service;

import hu.kriszprog.cvcreatorbackend.entity.CV;
import hu.kriszprog.cvcreatorbackend.entity.Candidate;
import hu.kriszprog.cvcreatorbackend.entity.Contact;
import hu.kriszprog.cvcreatorbackend.entity.SelfDefinition;
import hu.kriszprog.cvcreatorbackend.model.*;
import hu.kriszprog.cvcreatorbackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class CVProvider {

    @Autowired
    private CVRepository cvRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private SelfDefinitionRepository selfDefinitionRepository;

    public List<CVIdentifiersModel> getAllCVIdentifiers() {
        List<CV> cvList = cvRepository.findAll();
        List<CVIdentifiersModel> cvIdentifiersList = new ArrayList<>();

        for (CV cv : cvList) {
            cvIdentifiersList.add(extractIdentifiersFromCV(cv));
        }

        return cvIdentifiersList;
    }

    public CVModel getCVById(Long id) {
        CVIdentifiersModel cvIdentifiers = extractIdentifiersFromCV(cvRepository.getCVById(id));
        Candidate candidate = candidateRepository.getCandidateById(cvRepository.getCandidateIdByCVId(id));
        Contact contact = contactRepository.getContactById(cvRepository.getContactIdByCVId(id));
        SelfDefinition selfDefinition = selfDefinitionRepository.getSelfDefinitionById(cvRepository.getSelfDefinitionIdByCVId(id));

        return CVModel.builder()
                .cvIdentifiers(cvIdentifiers)
                .candidate(convertCandidateToCandidateModel(candidate))
                .contact(convertContactToContactModel(contact))
                .selfDefinition(convertSelfDefinitionToSelfDefinitionModel(selfDefinition))
                .build();
    }

    private CVIdentifiersModel extractIdentifiersFromCV(CV cv) {
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd" + " 'at' " + "HH:mm:ss")
                .format(cv.getCreationDate());

        return CVIdentifiersModel.builder()
                .id(cv.getId())
                .title(cv.getTitle())
                .creationDate(formattedDate)
                .build();
    }

    private CandidateModel convertCandidateToCandidateModel(Candidate candidate) {
        if (candidate == null) {
            return null;
        }

        return CandidateModel.builder()
                .id(candidate.getId())
                .name(candidate.getName())
                .role(candidate.getRole())
                .build();
    }

    private ContactModel convertContactToContactModel(Contact contact) {
        if (contact == null) {
            return null;
        }

        return ContactModel.builder()
                .id(contact.getId())
                .email(contact.getEmail())
                .phoneNr(contact.getPhoneNr())
                .linkedInProfile(contact.getLinkedInProfile())
                .build();
    }

    private SelfDefinitionModel convertSelfDefinitionToSelfDefinitionModel(SelfDefinition selfDefinition) {
        if (selfDefinition == null) {
            return null;
        }

        return SelfDefinitionModel.builder()
                .id(selfDefinition.getId())
                .selfDefinition(selfDefinition.getSelfDefinition())
                .build();
    }

    public CVIdentifiersModel addNewCV(CVIdentifiersModel cvIdentifiersModel) {
        CV newCV = cvRepository.save(
                CV.builder()
                .title(cvIdentifiersModel.getTitle())
                .build()
        );
        return extractIdentifiersFromCV(newCV);
    }
}

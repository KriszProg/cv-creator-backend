package hu.kriszprog.cvcreatorbackend.service;

import hu.kriszprog.cvcreatorbackend.entity.*;
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

    @Autowired
    private StrengthRepository strengthRepository;

    @Autowired
    private MentorOpinionRepository mentorOpinionRepository;

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
        Strength strength = strengthRepository.getStrengthById(cvRepository.getStrengthIdByCVId(id));
        MentorOpinion mentorOpinion = mentorOpinionRepository.getMentorOpinionById(cvRepository.getMentorOpinionIdByCVId(id));

        return CVModel.builder()
                .cvIdentifiers(cvIdentifiers)
                .candidate(candidate)
                .contact(contact)
                .selfDefinition(selfDefinition)
                .strength(strength)
                .mentorOpinion(mentorOpinion)
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

    public List<Candidate> getAllCandidate() {
        return candidateRepository.findAll();
    }

    public CVIdentifiersModel addNewCV(CVIdentifiersModel cvIdentifiersModel) {
        CV newCV = cvRepository.save(
                CV.builder()
                .title(cvIdentifiersModel.getTitle())
                .build()
        );
        return extractIdentifiersFromCV(newCV);
    }

    public void updateCandidateInCV(Long id, Candidate candidate) {
        CV existedCV = cvRepository.getCVById(id);
        existedCV.setCandidate(candidate);
        cvRepository.save(existedCV);
    }

    public void updateSelfDefinitionInCV(Long id, SelfDefinition selfDefinition) {
        CV existedCV = cvRepository.getCVById(id);
        SelfDefinition existedSelfDefinition = selfDefinitionRepository.getSelfDefinitionIfExist(selfDefinition.getSelfDefinition());
        SelfDefinition selfDefinitionForUpdate;

        if (existedSelfDefinition == null) {
             selfDefinitionForUpdate = SelfDefinition.builder()
                    .selfDefinition(selfDefinition.getSelfDefinition())
                    .build();
            selfDefinitionRepository.save(selfDefinitionForUpdate);
        } else {
            selfDefinitionForUpdate = existedSelfDefinition;
        }
        existedCV.setSelfDefinition(selfDefinitionForUpdate);
        cvRepository.save(existedCV);
    }

    public void updateStrengthInCV(Long id, Strength strength) {
        CV existedCV = cvRepository.getCVById(id);
        Strength existedStrength = strengthRepository.getStrengthIfExist(strength.getStrength());
        Strength strengthForUpdate;

        if (existedStrength == null) {
            strengthForUpdate = Strength.builder()
                    .strength(strength.getStrength())
                    .build();
            strengthRepository.save(strengthForUpdate);
        } else {
            strengthForUpdate = existedStrength;
        }
        existedCV.setStrength(strengthForUpdate);
        cvRepository.save(existedCV);
    }

    public void updateMentorOpinionInCV(Long id, MentorOpinion mentorOpinion) {
        CV existedCV = cvRepository.getCVById(id);
        MentorOpinion existedMentorOpinion = mentorOpinionRepository.getMentorOpinionIfExist(mentorOpinion.getMentorOpinion());
        MentorOpinion mentorOpinionForUpdate;

        if (existedMentorOpinion == null) {
            mentorOpinionForUpdate = MentorOpinion.builder()
                    .mentorOpinion(mentorOpinion.getMentorOpinion())
                    .build();
            mentorOpinionRepository.save(mentorOpinionForUpdate);
        } else {
            mentorOpinionForUpdate = existedMentorOpinion;
        }
        existedCV.setMentorOpinion(mentorOpinionForUpdate);
        cvRepository.save(existedCV);
    }

    public void updateContactInCV(Long id, Contact contact) {
        CV existedCV = cvRepository.getCVById(id);
        Contact existedContact = contactRepository.getContactIfExist(
                contact.getEmail(),
                contact.getPhoneNr(),
                contact.getLinkedInProfile()
        );
        System.out.println("existedContact: " + existedContact);
        Contact contactForUpdate;

        if (existedContact == null) {
            contactForUpdate = Contact.builder()
                    .email(contact.getEmail())
                    .phoneNr(contact.getPhoneNr())
                    .linkedInProfile(contact.getLinkedInProfile())
                    .build();
            contactRepository.save(contactForUpdate);
        } else {
            contactForUpdate = existedContact;
        }
        existedCV.setContact(contactForUpdate);
        cvRepository.save(existedCV);
    }
}

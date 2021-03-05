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
    private ImageRepository imageRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private PersonalInfoRepository personalInfoRepository;

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
        Image background = imageRepository.getImageById(cvRepository.getBackgroundIdByCVId(id));
        Image profilePhoto = imageRepository.getImageById(cvRepository.getProfilePhotoIdByCVId(id));
        Candidate candidate = candidateRepository.getCandidateById(cvRepository.getCandidateIdByCVId(id));
        Contact contact = contactRepository.getContactById(cvRepository.getContactIdByCVId(id));

        PersonalInfo section1 = personalInfoRepository.getPersonalInfoById(cvRepository.getPersInf1IdByCVId(id));
        PersonalInfo section2 = personalInfoRepository.getPersonalInfoById(cvRepository.getPersInf2IdByCVId(id));
        PersonalInfo section3 = personalInfoRepository.getPersonalInfoById(cvRepository.getPersInf3IdByCVId(id));

        return CVModel.builder()
                .cvIdentifiers(cvIdentifiers)
                .background(background)
                .profilePhoto(profilePhoto)
                .candidate(candidate)
                .contact(contact)
                .persInf1(section1)
                .persInf2(section2)
                .persInf3(section3)
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

    public CVIdentifiersModel addNewCV(CVIdentifiersModel cvIdentifiersModel) {
        CV newCV = cvRepository.save(
                CV.builder()
                    .title(cvIdentifiersModel.getTitle())
                    .build()
        );
        return extractIdentifiersFromCV(newCV);
    }

    public void updateTitleAndCandidateInCV(Long id, String title, Candidate candidate) {
        CV existedCV = cvRepository.getCVById(id);
        updateTitleInCV(title, existedCV);
        updateCandidateInCV(candidate, existedCV);
    }

    private void updateTitleInCV(String title, CV existedCV) {
        if (!existedCV.getTitle().equals(title)) {
            existedCV.setTitle(title);
            cvRepository.save(existedCV);
        }
    }

    private void updateCandidateInCV(Candidate candidate, CV existedCV) {
        Candidate existedCandidate = candidateRepository.getCandidateIfExist(
                candidate.getName(),
                candidate.getRole()
        );
        Candidate candidateForUpdate;

        if (existedCandidate == null) {
            candidateForUpdate = Candidate.builder()
                    .name(candidate.getName())
                    .role(candidate.getRole())
                    .build();
            candidateRepository.save(candidateForUpdate);
        } else {
            candidateForUpdate = existedCandidate;
        }
        existedCV.setCandidate(candidateForUpdate);
        cvRepository.save(existedCV);
    }

    public List<Candidate> getAllCandidate() {
        return candidateRepository.findAll();
    }

    public void updateImageInCV(Long id, Image image) {
        CV existedCV = cvRepository.getCVById(id);
        Image existedImage = imageRepository.getImageIfExist(image.getImageType(), image.getUrl());
        Image imageForUpdate;

        if (existedImage == null) {
            imageForUpdate = Image.builder()
                    .imageType(image.getImageType())
                    .url(image.getUrl())
                    .build();
            imageRepository.save(imageForUpdate);
        } else {
            imageForUpdate = existedImage;
        }

        switch (image.getImageType()) {
            case BACKGROUND:
                existedCV.setBackground(imageForUpdate);
                break;
            case PROFILE_PHOTO:
                existedCV.setProfilePhoto(imageForUpdate);
                break;
        }
        cvRepository.save(existedCV);
    }

    public void updateContactInCV(Long id, Contact contact) {
        CV existedCV = cvRepository.getCVById(id);
        Contact existedContact = contactRepository.getContactIfExist(
                contact.getEmail(),
                contact.getPhoneNr(),
                contact.getLinkedInProfile()
        );
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

    public void updatePersonalInfoInCV(Long id, PersonalInfo personalInfo) {
        CV existedCV = cvRepository.getCVById(id);
        PersonalInfo existedPersonalInfo = personalInfoRepository.getPersonalInfoIfExist(
                personalInfo.getPersonalInfoType(),
                personalInfo.getSectionTitle(),
                personalInfo.getText()
        );
        PersonalInfo personalInfoForUpdate;

        if (existedPersonalInfo == null) {
            System.out.println("Incoming PersonalInfo not exist...");
            personalInfoForUpdate = PersonalInfo.builder()
                    .personalInfoType(personalInfo.getPersonalInfoType())
                    .sectionTitle(personalInfo.getSectionTitle())
                    .text(personalInfo.getText())
                    .build();
            personalInfoRepository.save(personalInfoForUpdate);
            System.out.println("Newly created PersonalInfo is: " + personalInfoForUpdate);
        } else {
            System.out.println("Incoming PersonalInfo already exist...");
            personalInfoForUpdate = existedPersonalInfo;
        }

        switch (personalInfo.getPersonalInfoType()) {
            case PERS_INF_1:
                existedCV.setPersInf1(personalInfoForUpdate);
                break;
            case PERS_INF_2:
                existedCV.setPersInf2(personalInfoForUpdate);
                break;
            case PERS_INF_3:
                existedCV.setPersInf3(personalInfoForUpdate);
                break;
        }
        cvRepository.save(existedCV);
    }

}

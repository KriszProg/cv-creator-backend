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

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private QualificationRepository qualificationRepository;

    @Autowired
    private LanguageRepository languageRepository;

    public List<CVIdentifiersModel> getAllCVIdentifiers() {
        List<CV> cvList = cvRepository.findAll();
        List<CVIdentifiersModel> cvIdentifiersList = new ArrayList<>();

        for (CV cv : cvList) {
            cvIdentifiersList.add(extractIdentifiersFromCV(cv));
        }

        return cvIdentifiersList;
    }

    public CVModel getCVById(Long id) {
        CV existedCV = cvRepository.getCVById(id);
        CVIdentifiersModel cvIdentifiers = extractIdentifiersFromCV(existedCV);

        return CVModel.builder()
                .cvIdentifiers(cvIdentifiers)
                .background(existedCV.getBackground())
                .profilePhoto(existedCV.getProfilePhoto())
                .candidate(existedCV.getCandidate())
                .contact(existedCV.getContact())
                .persInf1(existedCV.getPersInf1())
                .persInf2(existedCV.getPersInf2())
                .persInf3(existedCV.getPersInf3())
                .projectList(existedCV.getProjectList())
                .jobList(existedCV.getJobList())
                .qualificationList(existedCV.getQualificationList())
                .languageList(existedCV.getLanguageList())
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
            personalInfoForUpdate = PersonalInfo.builder()
                    .personalInfoType(personalInfo.getPersonalInfoType())
                    .sectionTitle(personalInfo.getSectionTitle())
                    .text(personalInfo.getText())
                    .build();
            personalInfoRepository.save(personalInfoForUpdate);
        } else {
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

    public void updateProjectsInCV(Long id, List<Project> projectList) {
        CV existedCV = cvRepository.getCVById(id);
        List<Project> projectListForUpdate = new ArrayList<>();

        for (Project project : projectList) {
            Project existedProject = projectRepository.getProjectIfExist(
                    project.getTitle(),
                    project.getUrl1(),
                    project.getUrl2(),
                    project.getDescription()
                    );
            Project projectForUpdate;

            if (existedProject == null) {
                projectForUpdate = Project.builder()
                        .title(project.getTitle())
                        .url1(project.getUrl1())
                        .url2(project.getUrl2())
                        .description(project.getDescription())
                        .build();
                projectRepository.save(projectForUpdate);
            } else {
                projectForUpdate = existedProject;
            }
            projectListForUpdate.add(projectForUpdate);
        }
        existedCV.setProjectList(projectListForUpdate);
        cvRepository.save(existedCV);
    }

    public void updateJobsInCV(Long id, List<Job> jobList) {
        CV existedCV = cvRepository.getCVById(id);
        List<Job> jobListForUpdate = new ArrayList<>();

        for (Job job : jobList) {
            Job existedJob = jobRepository.getJobIfExist(
                    job.getRole(),
                    job.getYearFrom(),
                    job.getYearTo(),
                    job.getCompany()
            );
            Job jobForUpdate;

            if (existedJob == null) {
                jobForUpdate = Job.builder()
                        .role(job.getRole())
                        .yearFrom(job.getYearFrom())
                        .yearTo(job.getYearTo())
                        .company(job.getCompany())
                        .build();
                jobRepository.save(jobForUpdate);
            } else {
                jobForUpdate = existedJob;
            }
            jobListForUpdate.add(jobForUpdate);
        }
        existedCV.setJobList(jobListForUpdate);
        cvRepository.save(existedCV);
    }

    public void updateQualificationsInCV(Long id, List<Qualification> qualificationList) {
        CV existedCV = cvRepository.getCVById(id);
        List<Qualification> qualificationListForUpdate = new ArrayList<>();

        for (Qualification qualification : qualificationList) {
            Qualification existedQualification = qualificationRepository.getQualificationIfExist(
                    qualification.getName(),
                    qualification.getDegree(),
                    qualification.getYearFrom(),
                    qualification.getYearTo(),
                    qualification.getSchool(),
                    qualification.getCityOfSchool()
            );
            Qualification qualificationForUpdate;

            if (existedQualification == null) {
                qualificationForUpdate = Qualification.builder()
                        .name(qualification.getName())
                        .degree(qualification.getDegree())
                        .yearFrom(qualification.getYearFrom())
                        .yearTo(qualification.getYearTo())
                        .school(qualification.getSchool())
                        .cityOfSchool(qualification.getCityOfSchool())
                        .build();
                qualificationRepository.save(qualificationForUpdate);
            } else {
                qualificationForUpdate = existedQualification;
            }
            qualificationListForUpdate.add(qualificationForUpdate);
        }
        existedCV.setQualificationList(qualificationListForUpdate);
        cvRepository.save(existedCV);
    }

    public void updateLanguagesInCV(Long id, List<Language> languageList) {
        CV existedCV = cvRepository.getCVById(id);
        List<Language> languageListForUpdate = new ArrayList<>();

        for (Language language : languageList) {
            Language existedLanguage = languageRepository.getLanguageIfExist(
                    language.getLanguage(),
                    language.getLevel()
            );
            Language languageForUpdate;

            if (existedLanguage == null) {
                languageForUpdate = Language.builder()
                        .language(language.getLanguage())
                        .level(language.getLevel())
                        .build();
                languageRepository.save(languageForUpdate);
            } else {
                languageForUpdate = existedLanguage;
            }
            languageListForUpdate.add(languageForUpdate);
        }
        existedCV.setLanguageList(languageListForUpdate);
        cvRepository.save(existedCV);
    }

}

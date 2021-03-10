package hu.kriszprog.cvcreatorbackend.service;

import hu.kriszprog.cvcreatorbackend.entity.*;
import hu.kriszprog.cvcreatorbackend.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DBInitializer {

    @Autowired
    private CVRepository cvRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private PersonalInfoRepository personalInfoRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private QualificationRepository qualificationRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(DBInitializer.class);

    @PostConstruct
    public void postConstruct() {
        Image background1 = Image.builder()
                .imageType(ImageType.BACKGROUND)
                .url("https://res.cloudinary.com/kpimgstore/image/upload/v1614847692/testbackground.jpg")
                .build();

        Image background2 = Image.builder()
                .imageType(ImageType.BACKGROUND)
                .url("https://res.cloudinary.com/kpimgstore/image/upload/v1614864694/testbackground2.jpg")
                .build();

        Image profilePhoto1 = Image.builder()
                .imageType(ImageType.PROFILE_PHOTO)
                .url("https://res.cloudinary.com/kpimgstore/image/upload/v1614847548/testprofilephoto.jpg")
                .build();

        Image profilePhoto2 = Image.builder()
                .imageType(ImageType.PROFILE_PHOTO)
                .url("https://res.cloudinary.com/kpimgstore/image/upload/v1614935104/daviddeveloper.jpg")
                .build();

        Image profilePhoto3 = Image.builder()
                .imageType(ImageType.PROFILE_PHOTO)
                .url("https://res.cloudinary.com/kpimgstore/image/upload/v1614935131/dianadeveloper.jpg")
                .build();

        //Save IMAGES
        imageRepository.save(background1);
        imageRepository.save(background2);
        imageRepository.save(profilePhoto1);
        imageRepository.save(profilePhoto2);
        imageRepository.save(profilePhoto3);

        Candidate candidate1 = Candidate.builder()
                .name("Krisztián Kovács")
                .role("Full Stack Developer")
                .build();

        Candidate candidate2 = Candidate.builder()
                .name("David Developer")
                .role("Junior Java Developer")
                .build();

        Candidate candidate3 = Candidate.builder()
                .name("Diana Developer")
                .role("Senior Web Developer")
                .build();

        //Save CANDIDATES
        candidateRepository.save(candidate1);
        candidateRepository.save(candidate2);
        candidateRepository.save(candidate3);

        Contact contact1 = Contact.builder()
                .email("kovacs.krisztian@digikabel.hu")
                .phoneNr("+36 20 412-09-11")
                .linkedInProfile("https://www.linkedin.com/in/KrisztianKovacs-911/")
                .build();

        Contact contact2 = Contact.builder()
                .email("david.developer@hotmail.com")
                .phoneNr("+36 30 999-09-19")
                .linkedInProfile("https://www.linkedin.com/in/DavidDeveloper-Sample/")
                .build();

        Contact contact3 = Contact.builder()
                .email("diana.developer@gmail.com")
                .phoneNr("+36 70 111-14-24")
                .linkedInProfile("https://www.linkedin.com/in/DianaDeveloper-Sample/")
                .build();

        //Save CONTACTS
        contactRepository.save(contact1);
        contactRepository.save(contact2);
        contactRepository.save(contact3);

        PersonalInfo personalInfo1 = PersonalInfo.builder()
                .personalInfoType(PersonalInfoType.PERS_INF_1)
                .sectionTitle("Self Definition")
                .text("I'm easy going and patient. Have a lot of hobbies and mostly social and outgoing. " +
                        "I maintain good relations with colleagues and usually I'm optimistic or in worst case " +
                        "realistic. I like to help others but not afraid to ask for help either, sometimes a bit " +
                        "stubborn though. Overall, I'm a good team-player.")
                .build();

        PersonalInfo personalInfo2 = PersonalInfo.builder()
                .personalInfoType(PersonalInfoType.PERS_INF_2)
                .sectionTitle("Strength")
                .text("Has experience with Docker and container technologies and everything in-between. Good " +
                        "at presentations especially when it comes to backend discussions.")
                .build();

        PersonalInfo personalInfo3 = PersonalInfo.builder()
                .personalInfoType(PersonalInfoType.PERS_INF_3)
                .sectionTitle("Mentor Opinion")
                .text("Mentor opinion is shared across all generated CVs and it's your mentor's job to " +
                        "write one.")
                .build();

        //Save PERSONAL_INFOS
        personalInfoRepository.save(personalInfo1);
        personalInfoRepository.save(personalInfo2);
        personalInfoRepository.save(personalInfo3);

        List<Project> projectList = new ArrayList<>();

        projectList.add(Project.builder()
                .title("CV Creator")
                .url1("https://github.com/KriszProg/CV-creator-frontend")
                .url2("https://github.com/KriszProg/cv-creator-backend")
                .description("I've implemented a CV Creator web App in this private project, that you can use " +
                        "to create your CV and save as PDF. Frontend: ReactJS, React-pdf, Cloudinary; Backend: " +
                        "Java with SpringBoot, H2 database")
                .build());

        projectList.add(Project.builder()
                .title("Garage Store")
                .url1("https://github.com/KriszProg/garage-store")
                .url2("")
                .description("In this Team Work project we've implemented a Microservice Architecture based on " +
                        "the Netflix API Gateway Desing Pattern. Frontend: ReactJS; Backend: Java with SpringBoot, " +
                        "H2 database, Eureka Server, and Zuul")
                .build());

        projectList.add(Project.builder()
                .title("Harry Potter")
                .url1("https://github.com/KriszProg/harry-potter-frontend")
                .url2("https://github.com/KriszProg/harry-potter-backend")
                .description("The Web App which we've implemented as a Team Work project displays information " +
                        "about characters of Harry Potter movie in form of Cards. Frontend: ReactJs; Backend: " +
                        "Java with SpringBoot, H2 database, Spring-Security, JWT for authorization")
                .build());

        //Save PROJECTS
        projectRepository.saveAll(projectList);

        List<Job> jobList = new ArrayList<>();

        jobList.add(Job.builder()
                .role("Customs & ADR Department Manager, Quality Manager")
                .yearFrom(2004)
                .yearTo(2019)
                .company("DSV Hungária Kft.")
                .build());

        jobList.add(Job.builder()
                .role("Customs Team Leader")
                .yearFrom(1999)
                .yearTo(2004)
                .company("Frans Maas Hungária Kft.")
                .build());

        jobList.add(Job.builder()
                .role("Customs Clerk")
                .yearFrom(1998)
                .yearTo(1999)
                .company("Frans Maas Hungária Kft.")
                .build());

        //Save JOBS
        jobRepository.saveAll(jobList);

        List<Qualification> qualificationList = new ArrayList<>();

        qualificationList.add(Qualification.builder()
                .name("Junior Full Stack Developer")
                .degree("Intermediate")
                .yearFrom(2019)
                .yearTo(2021)
                .school("Codecool - Full Stack Developer Course")
                .cityOfSchool("Budapest")
                .build());

        qualificationList.add(Qualification.builder()
                .name("Customs Expert - based on the Union Customs Code")
                .degree("Intermediate")
                .yearFrom(2018)
                .yearTo(2018)
                .school("VJASZSZ - Authority Course")
                .cityOfSchool("Budapest")
                .build());

        qualificationList.add(Qualification.builder()
                .name("Dangerous Goods Transportation (ADR) Clerk")
                .degree("Intermediate")
                .yearFrom(2004)
                .yearTo(2004)
                .school("HVESZ Course")
                .cityOfSchool("Budapest")
                .build());

        qualificationList.add(Qualification.builder()
                .name("Customs Clerk")
                .degree("Intermediate")
                .yearFrom(1998)
                .yearTo(1998)
                .school("Kossuth Lajos Secondary School of Economics")
                .cityOfSchool("Budapest")
                .build());

        qualificationList.add(Qualification.builder()
                .name("Foreign Trade Clerk")
                .degree("Intermediate")
                .yearFrom(1997)
                .yearTo(1998)
                .school("Kossuth Lajos Secondary School of Economics")
                .cityOfSchool("Budapest")
                .build());

        //Save QUALIFICATIONS
        qualificationRepository.saveAll(qualificationList);

        List<CV> cvList = new ArrayList<>();

        cvList.add(CV.builder()
                .title("CV CV Creator's site owner")
                .background(background1)
                .profilePhoto(profilePhoto1)
                .candidate(candidate1)
                .contact(contact1)
                .persInf1(personalInfo1)
                .persInf2(personalInfo2)
                .persInf3(personalInfo3)
                .projectList(projectList)
                .jobList(jobList)
                .qualificationList(qualificationList)
                .build());

        cvList.add(CV.builder()
                .title("CV SAMPLE - IT - David")
                .background(background2)
                .profilePhoto(profilePhoto2)
                .candidate(candidate2)
                .contact(contact2)
                .persInf1(personalInfo1)
                .persInf2(personalInfo2)
                .persInf3(personalInfo3)
                .build());

        cvList.add(CV.builder()
                .title("CV SAMPLE - IT - Diana")
                .background(background2)
                .profilePhoto(profilePhoto3)
                .candidate(candidate3)
                .contact(contact3)
                .persInf1(personalInfo1)
                .persInf2(personalInfo2)
                .persInf3(personalInfo3)
                .build());

        cvList.add(CV.builder()
                .title("CV SAMPLE - Empty")
                .build());

        cvRepository.saveAll(cvList);

        LOGGER.info("Initializing database finished");
    }
}

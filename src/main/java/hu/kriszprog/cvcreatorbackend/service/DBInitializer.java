package hu.kriszprog.cvcreatorbackend.service;

import hu.kriszprog.cvcreatorbackend.entity.*;
import hu.kriszprog.cvcreatorbackend.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Autowired
    private LanguageRepository languageRepository;

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
        imageRepository.saveAll(Arrays.asList(
                background1,
                background2,
                profilePhoto1,
                profilePhoto2,
                profilePhoto3)
        );

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
        candidateRepository.saveAll(Arrays.asList(
                candidate1,
                candidate2,
                candidate3)
        );

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
        contactRepository.saveAll(Arrays.asList(
                contact1,
                contact2,
                contact3)
        );

        List<PersonalInfo> personalInfoList1 = new ArrayList<>();

        personalInfoList1.add(PersonalInfo.builder()
                .sectionTitle("Self Definition")
                .text("I’m like a “flexible cube,” which means I know my own limits, but I’m open for " +
                        "changes and self-improvement. Some values which I consider as most important " +
                        "both at work and in private life are: courage, fun, quality and proper communication. " +
                        "My first and so far only job was at a multinational logistic company DSV Hungária Kft. " +
                        "(former name: Frans Maas Hungária Kft.), where I spent 20 and a half years in various " +
                        "roles. Whatever task I was entrusted with, I was always interested in how to make it" +
                        "as structured, automatized, easier, faster so as effective as possible. Among other " +
                        "things, this led to choose software development as my future career. I like to " +
                        "spend my free time with friends and family, doing creative activities such" +
                        "as DIY, or act as a volunteer.")
                .build()
        );

        personalInfoList1.add(PersonalInfo.builder()
                .sectionTitle("Strength")
                .text("Backend technologies are closer to me. I've worked with Java, SpringBoot, " +
                        "Hibernate, H2 and Postgresql, Maven, I know and used Git and Agile methodology " +
                        "as well. Beside these I've some experience about ReactJS and general JS" +
                        "as well. I consider following skills as my strengths: structuring, proper " +
                        "communication, strategic-approach, cooperation, teamwork, planning, organizing," +
                        "problem solving, process development.")
                .build()
        );

        personalInfoList1.add(PersonalInfo.builder()
                .sectionTitle("Mentor Opinion")
                .text("'Krisztián is one of the most dedicated person I've ever met. He put enormous " +
                        "effort into learning and he does it consciously. He does not just focus on " +
                        "his personal improvement, but on the development of his team. He gives" +
                        "positive and constructive feedback frequently and open to receive ones which " +
                        "he can grow from. His communication is more than professional. He is assertive, " +
                        "friendly and helpful in the same time.' - Laszlo Terray, mentor")
                .build()
        );

//        Save PERSONALINFO LIST1
        personalInfoRepository.saveAll(personalInfoList1);

        List<PersonalInfo> personalInfoList2 = new ArrayList<>();

        personalInfoList2.add(PersonalInfo.builder()
                .sectionTitle("Self Definition")
                .text("I'm easy going and patient. Have a lot of hobbies and mostly social and outgoing. " +
                        "I maintain good relations with colleagues and usually I'm optimistic or in worst case " +
                        "realistic. I like to help others but not afraid to ask for help either, sometimes a bit " +
                        "stubborn though. Overall, I'm a good team-player.")
                .build()
        );

        personalInfoList2.add(PersonalInfo.builder()
                .sectionTitle("Strength")
                .text("Has experience with Docker and container technologies and everything in-between. Good " +
                        "at presentations especially when it comes to backend discussions.")
                .build()
        );

        personalInfoList2.add(PersonalInfo.builder()
                .sectionTitle("Mentor Opinion")
                .text("Mentor opinion is shared across all generated CVs and it's your mentor's job to " +
                        "write one.")
                .build()
        );

        // Save PERSONALINFO LIST2
        personalInfoRepository.saveAll(personalInfoList2);

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

        List<Language> languageList = new ArrayList<>();

        languageList.add(Language.builder()
                .language("Hungarian")
                .level("Native")
                .build()
        );

        languageList.add(Language.builder()
                .language("English")
                .level("Intermediate")
                .build()
        );

        //Save LANGUAGES
        languageRepository.saveAll(languageList);

        List<CV> cvList = new ArrayList<>();

        cvList.add(CV.builder()
                .title("CV of CV-Creator's owner")
                .background(background1)
                .profilePhoto(profilePhoto1)
                .candidate(candidate1)
                .contact(contact1)
                .personalInfoList(personalInfoList1)
                .projectList(projectList)
                .jobList(jobList)
                .qualificationList(qualificationList)
                .languageList(languageList)
                .build());

        cvList.add(CV.builder()
                .title("CV SAMPLE - IT - David")
                .background(background2)
                .profilePhoto(profilePhoto2)
                .candidate(candidate2)
                .contact(contact2)
                .personalInfoList(personalInfoList2)
                .build());

        cvList.add(CV.builder()
                .title("CV SAMPLE - IT - Diana")
                .background(background2)
                .profilePhoto(profilePhoto3)
                .candidate(candidate3)
                .contact(contact3)
                .personalInfoList(personalInfoList2)
                .build());

        cvList.add(CV.builder()
                .title("CV SAMPLE - Empty")
                .build());

        cvRepository.saveAll(cvList);

        LOGGER.info("Initializing database finished");
    }
}

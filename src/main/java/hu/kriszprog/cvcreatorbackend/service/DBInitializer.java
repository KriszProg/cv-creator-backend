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
    private SelfDefinitionRepository selfDefinitionRepository;

    @Autowired
    private StrengthRepository strengthRepository;

    @Autowired
    private MentorOpinionRepository mentorOpinionRepository;

    @Autowired
    private ImageRepository imageRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(DBInitializer.class);

    @PostConstruct
    public void postConstruct() {
        Image background1 = Image.builder()
                .imageType(ImageType.BACKGROUND)
                .url("https://res.cloudinary.com/kpimgstore/image/upload/v1614847692/testbackground.jpg")
                .build();

        Image profilePhoto1 = Image.builder()
                .imageType(ImageType.PROFILE_PHOTO)
                .url("https://res.cloudinary.com/kpimgstore/image/upload/v1614847548/testprofilephoto.jpg")
                .build();

        //Save IMAGES
        imageRepository.save(background1);
        imageRepository.save(profilePhoto1);

        Candidate candidate1 = Candidate.builder()
                .name("Krisztián Kovács")
                .role("Full Stack Developer")
                .build();

        Candidate candidate2 = Candidate.builder()
                .name("Krisztián Kovács")
                .role("Java Developer")
                .build();

        //Save CANDIDATES
        candidateRepository.save(candidate1);
        candidateRepository.save(candidate2);

        Contact contact1 = Contact.builder()
                .email("kovacs.krisztian@digikabel.hu")
                .phoneNr("+36 20 412-09-11")
                .linkedInProfile("https://www.linkedin.com/in/KrisztianKovacs-911/")
                .build();

        //Save CONTACT(S)
        contactRepository.save(contact1);

        SelfDefinition selfDefinition1 = SelfDefinition.builder()
                .selfDefinition("I'm easy going and patient. Have a lot of hobbies and mostly social and outgoing. " +
                        "I maintain good relations with colleagues and usually I'm optimistic or in worst case " +
                        "realistic. I like to help others but not afraid to ask for help either, sometimes a bit " +
                        "stubborn though. Overall, I'm a good team-player.")
                .build();

        //Save SELF_DEFINITION(S)
        selfDefinitionRepository.save(selfDefinition1);

        Strength strength1 = Strength.builder()
                .strength("Has experience with Docker and container technologies and everything in-between. Good " +
                        "at presentations especially when it comes to backend discussions.")
                .build();

        //Save STRENGTH(S)
        strengthRepository.save(strength1);

        MentorOpinion mentorOpinion1 = MentorOpinion.builder()
                .mentorOpinion("Mentor opinion is shared across all generated CVs and it's your mentor's job to " +
                        "write one.")
                .build();

        //Save MENTOR_OPINION(S)
        mentorOpinionRepository.save(mentorOpinion1);

        List<CV> cvList = new ArrayList<>();

        cvList.add(CV.builder()
                .title("CV for Google")
                .background(background1)
                .profilePhoto(profilePhoto1)
                .candidate(candidate1)
                .contact(contact1)
                .selfDefinition(selfDefinition1)
                .strength(strength1)
                .mentorOpinion(mentorOpinion1)
                .build());

        cvList.add(CV.builder()
                .title("CV for Microsoft - Full Stack")
                .candidate(candidate1)
                .contact(contact1)
                .build());

        cvList.add(CV.builder()
                .title("CV for Microsoft - Java")
                .candidate(candidate2)
                .contact(contact1)
                .build());

        cvList.add(CV.builder()
                .title("New Empty CV for Microsoft")
                .build());

        cvRepository.saveAll(cvList);

        LOGGER.info("Initializing database finished");
    }
}

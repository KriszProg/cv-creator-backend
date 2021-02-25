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

    private static final Logger LOGGER = LoggerFactory.getLogger(DBInitializer.class);

    @PostConstruct
    public void postConstruct() {
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

        List<CV> cvList = new ArrayList<>();

        cvList.add(CV.builder()
                .title("CV for Google")
                .candidate(candidate1)
                .contact(contact1)
                .selfDefinition(selfDefinition1)
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

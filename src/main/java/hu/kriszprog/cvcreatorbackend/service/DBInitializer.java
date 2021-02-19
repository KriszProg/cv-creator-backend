package hu.kriszprog.cvcreatorbackend.service;

import hu.kriszprog.cvcreatorbackend.entity.CVTitle;
import hu.kriszprog.cvcreatorbackend.repository.CVTitleRepository;
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
    private CVTitleRepository cvTitleRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(DBInitializer.class);

    @PostConstruct
    public void postConstruct() {
        LOGGER.info("Initializing database");
        List<CVTitle> cvTitleList = new ArrayList<>();

        cvTitleList.add(CVTitle.builder()
                .title("CV for Google")
                .build());

        cvTitleList.add(CVTitle.builder()
                .title("CV for Microsoft")
                .build());

        cvTitleList.add(CVTitle.builder()
                .title("CV for Amazon")
                .build());

        cvTitleRepository.saveAll(cvTitleList);

        LOGGER.info("Initializing database finished");
    }
}

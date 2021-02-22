package hu.kriszprog.cvcreatorbackend.service;

import hu.kriszprog.cvcreatorbackend.entity.CVTitle;
import hu.kriszprog.cvcreatorbackend.model.CVTitleModel;
import hu.kriszprog.cvcreatorbackend.repository.CVTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class CVProvider {

    @Autowired
    private CVTitleRepository cvTitleRepository;

    public List<CVTitleModel> getAllCV() {
        List<CVTitle> cvTitleList = cvTitleRepository.findAll();
        List<CVTitleModel> cvTitleModelList = new ArrayList<>();

        for (CVTitle cvTitle : cvTitleList) {
            cvTitleModelList.add(convertCVTitleToCVTitleModel(cvTitle));
        }

        return cvTitleModelList;
    }

    public CVTitleModel getCVById(Long id) {
        CVTitle cvTitle = cvTitleRepository.getCVTitleById(id);
        return convertCVTitleToCVTitleModel(cvTitle);
    }

    private CVTitleModel convertCVTitleToCVTitleModel(CVTitle cvTitle) {
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd" + " 'at' " + "HH:mm:ss")
                .format(cvTitle.getCreationDate());

        return CVTitleModel.builder()
                .id(cvTitle.getId())
                .title(cvTitle.getTitle())
                .creationDate(formattedDate)
                .build();
    }

    public CVTitleModel addNewCV(CVTitleModel cvTitleModel) {
        CVTitle newCVTitle = cvTitleRepository.save(
                CVTitle.builder()
                .title(cvTitleModel.getTitle())
                .build()
        );
        return convertCVTitleToCVTitleModel(newCVTitle);
    }
}

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
        return convertCVTitlesToCVTitleModels(cvTitleList);
    }

    private List<CVTitleModel> convertCVTitlesToCVTitleModels(List<CVTitle> cvTitleList) {
        List<CVTitleModel> cvTitleModelList = new ArrayList<>();

        for (CVTitle cvTitle : cvTitleList) {
            String formattedDate = new SimpleDateFormat("yyyy-MM-dd" + " 'at' " + "HH:mm:ss")
                    .format(cvTitle.getCreationDate());

            cvTitleModelList.add(
                    CVTitleModel.builder()
                    .id(cvTitle.getId())
                    .title(cvTitle.getTitle())
                    .creationDate(formattedDate)
                    .build()
            );
        }
        return cvTitleModelList;
    }
}

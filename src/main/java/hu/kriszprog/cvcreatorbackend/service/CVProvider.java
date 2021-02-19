package hu.kriszprog.cvcreatorbackend.service;

import hu.kriszprog.cvcreatorbackend.entity.CVTitle;
import hu.kriszprog.cvcreatorbackend.repository.CVTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CVProvider {

    @Autowired
    private CVTitleRepository cvTitleRepository;

    public List<CVTitle> getAllCV() {
        return cvTitleRepository.findAll();
    }
}

package com.JEE_Project.JEE_Project.Services;

import com.JEE_Project.JEE_Project.Models.Programme_Activite;
import com.JEE_Project.JEE_Project.Repositories.RepoProgramme_Activite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceProgramme_Activite {

    @Autowired
    private RepoProgramme_Activite repoProgramme_Activite;

    @Transactional
    public void deleteActiviteFromProgramme(long programmeId, long activiteId) {
        repoProgramme_Activite.deleteActiviteFromProgramme(programmeId, activiteId);
    }

    @Transactional
    public void deleteAllActiviteFromProgramme(long programmeId) {
        repoProgramme_Activite.deleteAllActiviteFromProgramme(programmeId);
    }

    @Transactional
    public void addActiviteForProgramme(Programme_Activite programmeActivite) {
        repoProgramme_Activite.save(programmeActivite);
    }
}

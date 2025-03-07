package com.JEE_Project.JEE_Project.Services;

import com.JEE_Project.JEE_Project.Models.Activite;
import com.JEE_Project.JEE_Project.Models.Programme;
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
    public void deleteActiviteFromProgramme(Programme programme, Activite activite) {
        repoProgramme_Activite.deleteActiviteFromProgramme(programme, activite);
    }

    @Transactional
    public void deleteAllActiviteFromProgramme(Programme programme) {
        repoProgramme_Activite.deleteAllActiviteFromProgramme(programme);
    }

    @Transactional
    public void addActiviteForProgramme(Programme_Activite programmeActivite) {
        repoProgramme_Activite.save(programmeActivite);
    }
}

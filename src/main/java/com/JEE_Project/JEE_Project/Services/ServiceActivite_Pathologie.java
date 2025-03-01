package com.JEE_Project.JEE_Project.Services;

import com.JEE_Project.JEE_Project.Repositories.RepoActivite_Pathologie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceActivite_Pathologie {

    @Autowired
    private RepoActivite_Pathologie repoActivite_Pathologie;
}

package com.example.amicale.Data.Services;

import com.example.amicale.Data.Entity.Ressources;
import com.example.amicale.Data.Enumeration.TypeEcole;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RessourceServices {


    List<Ressources> getAllRessources();
    List<Ressources> getAllRessourcesByEcole(String ecole);
    public Ressources saveRessources(MultipartFile file,Ressources ressources)throws IOException;
}

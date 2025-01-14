package com.example.amicale.Data.Services.Impl;

import com.example.amicale.Data.Entity.Ecole;
import com.example.amicale.Data.Entity.Ressources;
import com.example.amicale.Data.Enumeration.TypeEcole;
import com.example.amicale.Data.Repository.EcoleRepository;
import com.example.amicale.Data.Repository.RessourcesRepository;
import com.example.amicale.Data.Services.RessourceServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Service
@AllArgsConstructor
public class RessourceServicesImpl implements RessourceServices {

    private final RessourcesRepository ressourcesrepository;
    private final EcoleRepository ecolerepository;
    public static String UPLOAD_DIR_R ="src/main/resources/static/ressources/";


    @Override
    public List<Ressources> getAllRessources() {
        return ressourcesrepository.findAll();
    }

    @Override
    public List<Ressources> getAllRessourcesByEcole(String ecole) {
        Ecole ecole1 = ecolerepository.findByEcoleName(ecole);

        if(ecole1==null){
            return null;
        }
        return ressourcesrepository.findByEcoles(ecole1);
    }

    @Override
    public Ressources saveRessources(MultipartFile file, Ressources ressources)throws IOException {

        //recupere d'abord le nom du fichier
        String filename= file.getOriginalFilename();


        //créer le path
        Path path = Paths.get(UPLOAD_DIR_R + filename);
        Files.write(path, file.getBytes());


        Ressources ressources1 = new Ressources();
        ressources1.setPath(filename);

        return ressourcesrepository.save(ressources1);
    }
}

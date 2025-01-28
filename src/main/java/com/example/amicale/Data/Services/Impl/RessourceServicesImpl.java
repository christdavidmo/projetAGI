package com.example.amicale.Data.Services.Impl;

import com.example.amicale.Data.Entity.Ecole;
import com.example.amicale.Data.Entity.Ressources;
import com.example.amicale.Data.Enumeration.TypeEcole;
import com.example.amicale.Data.Enumeration.TypeRessource;
import com.example.amicale.Data.Repository.EcoleRepository;
import com.example.amicale.Data.Repository.RessourcesRepository;
import com.example.amicale.Data.Services.RessourceServices;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;


@Service
@AllArgsConstructor
public class RessourceServicesImpl implements RessourceServices {

    private final RessourcesRepository ressourcesrepository;
    private final EcoleRepository ecolerepository;
    public static String UPLOAD_DIR_R ="src/main/resources/static/ressources/";




    @Override
    public Page<Ressources> getAllRessourcesPage(String titleRessource, Pageable pageable) {

        if(titleRessource != null && !titleRessource.isEmpty() ){
              Page<Ressources> RT = ressourcesrepository.findByTitleContaining(titleRessource, pageable);
              System.out.println("la ressource trouvée :"+ RT);
              return RT ;
        }
         Page<Ressources> TR = ressourcesrepository.findAll(pageable);
            System.out.println("toutes les ressources trouvées :"+ TR);
            return TR ;

    }

    @Override
    public Page<Ressources> getAllRessourcesByEcole(Pageable pageable,String ecole) {
        Ecole ecole1 = ecolerepository.findByEcoleName(ecole);

        if(ecole1==null){
            return null;
        }
       return ressourcesrepository.findByEcole(pageable,ecole1);
    }

    @Override
    public Page<Ressources> getAllRessources(Pageable pageable) {
        return ressourcesrepository.findAll(pageable);
    }

    @Override
    public Ressources saveRessources(MultipartFile file, TypeRessource type,Long ecoleId)throws IOException {

        //cherche aussi l'ecole
        Ecole ecole = ecolerepository.findById(ecoleId).get();

        //recupere d'abord le nom du fichier
        String filename= file.getOriginalFilename();


        //créer le path
        Path path = Paths.get(UPLOAD_DIR_R + filename);
        Files.write(path, file.getBytes());


        Ressources ressources1 = new Ressources();
        ressources1.setPath(filename);
        ressources1.setType(type);
        ressources1.setEcole(ecole);

        return ressourcesrepository.save(ressources1);
    }
}

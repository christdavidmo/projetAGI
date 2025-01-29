package com.example.amicale.Data.Services;

import com.example.amicale.Data.Entity.Ressources;
import com.example.amicale.Data.Enumeration.TypeEcole;
import com.example.amicale.Data.Enumeration.TypeRessource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RessourceServices {


    Page<Ressources> getAllRessourcesPage(String titleRessource, Pageable pageable);
    Page<Ressources> getAllRessourcesByEcole( Pageable pageable,String ecole,String ressourceName);
    Page<Ressources> getAllRessources(Pageable pageable);

    public Ressources saveRessources(MultipartFile file, TypeRessource type, Long ecoleId)throws IOException;

}

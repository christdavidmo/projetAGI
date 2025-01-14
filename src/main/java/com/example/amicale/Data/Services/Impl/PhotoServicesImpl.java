package com.example.amicale.Data.Services.Impl;

import com.example.amicale.Data.Entity.Evenement;
import com.example.amicale.Data.Entity.Photo;
import com.example.amicale.Data.Repository.PhotoRepository;
import com.example.amicale.Data.Services.PhotoServices;
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
public class PhotoServicesImpl implements PhotoServices {

    private static String UPLOAD_DIR = "src/main/resources/static/uploads/";
    private final PhotoRepository photoRepository;

    @Override
    public List<Photo> getAllImagesToEvenement(Long id) {
        return photoRepository.findPhotosByEvenementId(id);
    }

    @Override
    public Photo saveImage(MultipartFile file, Evenement evenement) throws IOException {

        // Récupère le nom du fichier
        String filename = file.getOriginalFilename();

        // Crée le chemin du fichier
        Path path = Paths.get(UPLOAD_DIR + filename); // Sauvegarde le fichier dans le répertoire static/uploads
        Files.write(path, file.getBytes());

        // Crée une nouvelle photo et enregistre son chemin relatif
        Photo photo = new Photo();
        photo.setEvenement(evenement);
        photo.setPath(filename); // Enregistre seulement le nom du fichier dans la base de données

        // Sauvegarde l'entité photo
        return photoRepository.save(photo);

    }
}

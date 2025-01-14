package com.example.amicale.Data.Services;

import com.example.amicale.Data.Entity.Evenement;
import com.example.amicale.Data.Entity.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PhotoServices {

    List<Photo> getAllImagesToEvenement(Long id);

    public Photo saveImage(MultipartFile file, Evenement evenement) throws IOException;
}

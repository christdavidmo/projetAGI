package com.example.amicale.Data.Services.Impl;

import com.example.amicale.Data.Entity.Photo;
import com.example.amicale.Data.Repository.PhotoRepository;
import com.example.amicale.Data.Services.PhotoServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PhotoServicesImpl implements PhotoServices {

    private final PhotoRepository photoRepository;

    @Override
    public List<Photo> getAllToEvenement(Long id) {
        return photoRepository.findPhotosByEvenementId(id);
    }
}

package com.example.amicale.Data.Services;

import com.example.amicale.Data.Entity.Photo;

import java.util.List;

public interface PhotoServices {

    List<Photo> getAllToEvenement(Long id);
}

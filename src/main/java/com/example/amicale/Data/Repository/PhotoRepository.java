package com.example.amicale.Data.Repository;

import com.example.amicale.Data.Entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    List<Photo> findPhotosByEvenementId(long evenementId);
}

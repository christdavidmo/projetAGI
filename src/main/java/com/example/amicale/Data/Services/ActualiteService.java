package com.example.amicale.Data.Services;

import com.example.amicale.Data.Entity.Actualite;

public interface ActualiteService {

   Actualite getActualiteByName(String name);
   Actualite getActualiteById(Long id);

   Boolean existActualiteById(Long id);
}

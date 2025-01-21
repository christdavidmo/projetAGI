package com.example.amicale.Data.Fixtures;

import com.example.amicale.Data.Entity.Ecole;
import com.example.amicale.Data.Repository.EcoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;


//@Order(5)
//@Component
@RequiredArgsConstructor
public class EcoleFixtures implements CommandLineRunner {

    private final EcoleRepository ecoleRepository;


    @Override
    public void run(String... args) throws Exception {

        List<String> ecoles = List.of("Ingenieur","Madiba","Droit","Management");
        // Créer et enregistrer une nouvelle école pour chaque nom d'école
        for (String ec : ecoles) {
            Ecole ecole = new Ecole();
            ecole.setEcoleName(ec);  // Définir le nom de l'école
            ecole.setActive(true);    // Marquer l'école comme active
            ecoleRepository.save(ecole); // Enregistrer l'école dans la base de données

        }
    }
}

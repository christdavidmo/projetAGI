package com.example.amicale.Data.Repository;

import com.example.amicale.Data.Entity.Ecole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EcoleRepository extends JpaRepository<Ecole, Long> {

    Ecole findByEcoleName(String name);
}

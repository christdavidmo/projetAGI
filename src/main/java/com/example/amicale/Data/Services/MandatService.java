package com.example.amicale.Data.Services;

import com.example.amicale.Data.Entity.Mandat;
import com.example.amicale.Data.Entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MandatService {
    Page<Mandat> GetAllMandat(Pageable pageable);
   List<Member> GetMandatById(Long MandatId);
}

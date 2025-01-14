package com.example.amicale.Data.Services;

import com.example.amicale.Data.Entity.Mandat;
import com.example.amicale.Data.Entity.Member;
import com.example.amicale.Data.Entity.MemberMandatRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface MandatService {

    Mandat saveMandat(Mandat mandat);
    Mandat getMandatById(Long id);

    List<Member> GetMandatById(Long MandatId);
    List<Member> getMemberWithRoles(Long MandatId);

    Page<Mandat> GetAllMandat(Pageable pageable);
    Page<Mandat> GetMandatByDate(LocalDate dateDebut,Pageable pageable);


}

package com.jamddo.speetto.repository;

import com.jamddo.speetto.domain.Speetto;
import org.springframework.data.jpa.repository.JpaRepository;



public interface SpeettoRepository extends JpaRepository<Speetto,Long>, SpeettoRepositoryCustom {

}

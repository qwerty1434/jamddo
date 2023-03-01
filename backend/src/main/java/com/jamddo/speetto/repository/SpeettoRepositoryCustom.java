package com.jamddo.speetto.repository;

import com.jamddo.speetto.domain.Speetto;
import com.jamddo.speetto.dto.SpeettoDto;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;


public interface SpeettoRepositoryCustom {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Speetto findUniqueDataAndGetEntity();


    SpeettoDto findUniqueDataAndGetDto();


}

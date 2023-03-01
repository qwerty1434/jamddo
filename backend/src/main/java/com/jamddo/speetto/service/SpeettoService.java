package com.jamddo.speetto.service;

import com.jamddo.speetto.domain.Speetto;
import com.jamddo.speetto.dto.SpeettoDto;
import com.jamddo.speetto.repository.SpeettoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class SpeettoService {
    private final SpeettoRepository speettoRepository;

    public int buyOne(){
        Speetto speetto = speettoRepository.findUniqueDataAndGetEntity();
        sleep(1000);
        return speetto.buyOne();
    }

    public SpeettoDto getData() {
        return speettoRepository.findUniqueDataAndGetDto();
    }

    private void sleep(int millis){
        try{
            Thread.sleep(millis);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }


}

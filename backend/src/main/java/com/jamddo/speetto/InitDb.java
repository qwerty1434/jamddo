package com.jamddo.speetto;

import com.jamddo.speetto.domain.Speetto;
import com.jamddo.speetto.repository.SpeettoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitDb {
    private final SpeettoRepository speettoRepository;

    @PostConstruct
    public void initialization() throws Exception{
        Speetto result = speettoRepository.findUniqueDataAndGetEntity();
        if(result == null){
            Speetto speetto = new Speetto();
            speettoRepository.save(speetto);
            log.info("초기화 데이터 삽입 완료");
        }else{
            log.info("DB에 존재하는 데이터를 활용하겠습니다");
        }
    }
}

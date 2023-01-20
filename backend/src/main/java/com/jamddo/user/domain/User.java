package com.jamddo.user.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String nickname;

    private String password;

    private long point;
    private long cnt;

    public void Reset(){
        this.point = 0L;
        this.cnt = 0L;
    }

    public void substractPoint(){
        this.point -= 1000L;
    }
    public void addPoint(long point){
        this.point += point;
    }
    public void addCnt(){
        this.cnt+=1;
    }



}

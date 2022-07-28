package com.sparta.week03.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class BoardRequestDto {

    private String username;    // 이 값들을 설정해주는건 Spring 이 알아서 해준다.
    private String contents;    // request 받을때 RequestBody 에서.
    private String title;
    private String password;
    private String passwordcheck;
}
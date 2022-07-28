package com.sparta.week03.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 만들어주는 어노테이션.
@Getter     // 게터 자동 생성 어노테이션
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class NoticeBoard extends Timestamped { // 생성,수정 시간을 자동으로 만들어주는걸 상속받음.
    @GeneratedValue(strategy = GenerationType.AUTO)     // Primary Key 인 id 값을 자동으로 증가시켜준다.
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String contents;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String password;

    public NoticeBoard(String username, String contents, String password, String title) {  // 생성자
        this.username = username;
        this.contents = contents;
        this.password = password;
        this.title = title;
    }

    public NoticeBoard(BoardRequestDto requestDto) {        // 이부분은 RequestDto 를 만들어줘야 함. (requestDto 는 요청이 들어온 재료)
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.password = requestDto.getPassword();
    }

    public void update(BoardRequestDto requestDto) {
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.password = requestDto.getPassword();
    }

}
package com.sparta.week03.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository            // MemoRepository 는 JpaRepository 로 부터 메소드들을 상속받아 쓴다.
public interface BoardRepository extends JpaRepository<NoticeBoard, Long> {  // Memo 라는 클래스이고 Id는 Long 타입인 녀석에게 쓸거다.
    List<NoticeBoard> findAllByOrderByCreatedAtDesc();
}
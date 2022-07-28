package com.sparta.week03.service;

import com.sparta.week03.domain.BoardRepository;
import com.sparta.week03.domain.BoardRequestDto;
import com.sparta.week03.domain.NoticeBoard;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@RequiredArgsConstructor    // final 로 선언된 녀석이 있으면 필요
@Service        // 스프링에게 이 클래스가 서비스 인것을 알려준다
public class BoardService {

    private final BoardRepository boardRepository;    // find 메소드를 사용하기 위해 Repo 를 멤버 변수로 추가

    // @Transactional 은 이게 정말 DB에 반영이 되어야 한다고 알려준다.
    @Transactional                                              // public 반환타입 메소드이름(재료,파라미터){ }
    public String update(Long id, BoardRequestDto requestDto) {    // 재료는 id와 업데이트할때 필요한 정보를 물고다니는 Dto를 받는다
        NoticeBoard board = boardRepository.findById(id).orElseThrow(    // 업데이트 하려면 먼저 업데이트가 필요한 녀석을 찾는다. * 그래서 찾으려면 find를 써야하는데 그러기위해서 Repo가 필요하다. ->15번 줄
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );     // find 하는데, 없다면 orElseThrow로 오류를 일으켜라. new 마음에 드는 오류 형 선택("아이디가 존재하지 않습니다."
        if (board.getPassword().equals(requestDto.getPassword())) {
            board.update(requestDto);
            return "게시글이 수정 되었습니다";
        } else {
            return "비밀번호가 일치하지 않습니다";
        }
    }
}
//
//    public void checkPassword(String checkPassword, Long id) throws Exception {
//        NoticeBoard board = boardRepository.findById(id).orElseThrow(() -> new Exception("회원이 존재하지 않습니다"));
//
//        if(!board.matchPassword(password, checkPassword) ) {
//            throw new Exception("비밀번호가 일치하지 않습니다.");
//        }
//
//        boardRepository.delete(board);
//    }


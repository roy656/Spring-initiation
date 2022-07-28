package com.sparta.week03.controller;
import com.sparta.week03.domain.BoardRepository;
import com.sparta.week03.domain.BoardRequestDto;
import com.sparta.week03.domain.NoticeBoard;
import com.sparta.week03.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.StoredProcedureParameter;
import java.util.List;

@RequiredArgsConstructor    // <- 이걸 넣어주면 밑의 MemoRepository 나 Service 들의 new 어쩌구 를 자동으로 생성해준다.
@RestController // json 방식으로
public class BoardController {
    private final BoardRepository boardRepository;        // MemoController 클래스의 멤버 변수
    private final BoardService boardService;



                                    // @RequestBody 가 꼭필요한데, post요청을 할때 데이터 내용이 들어있는곳이 Body임.
    @PostMapping("/api/noticeboards")  // .api/memos 로 POST 요청이 들어 왔을때 아래의 메소드를 실행해라 라는 뜻
    public NoticeBoard postBoard(@RequestBody BoardRequestDto requestDto) {     // 리턴타입은 Memo?, 생성할 메모의 데이터를 받아야해서 재료는 Dto
        NoticeBoard board = new NoticeBoard(requestDto);    // 메모를 저장하려면 Memo 객체를 생성 해줘야함. reQuestDto 의 데이터가 memo에 할당
        return boardRepository.save(board);   // 메모Repo 의 save 메소드를 이용해서 윗줄에서 데이터를 할당 받은 memo 를 저장.
                                            // 결국, 저장하고픈 메모 데이터는 -> requestDto -> memo 로 이동 저장되어 반환.
    }

//    @PostMapping("/api/noticeboards/pwcheck")
//    public String pwCheck(@RequestParam String id, @RequestParam String password Model model) {
//        if (id.equals(password)) {
//            model.addtribute()
//        }
//    }

        // 전체 데이터 조회
    @GetMapping("/api/noticeboards")   // .api/memos 로 GET 요청이 들어 왔을때 아래의 메소드를 실행해라 라는 뜻
    public List<NoticeBoard> getAllBoard() {   // 반환만 해주니까 필요한 재료는 없고, DB에 있는 리스트를 메모Repo의 find 메소드로 찾아서 반환
        return boardRepository.findAllByOrderByCreatedAtDesc(); // 하라는 getMemo 라는 이름의 메소드
    }


        // 삭제
    @DeleteMapping("/api/noticeboards/{id}")    // .api/memos/{id} 로 Delete 요청이 들어 왔을때 아래의 메소드를 실행해라 라는 뜻
    public Long deleteBoard(@PathVariable Long id) {     // deleteMemo 메소드는 재료인 id가 뭔지 모르기때문에 주소에 있는 id
        boardRepository.deleteById(id);                  // 를 변수로 받기 위해서 @PathVariable 를 씀. 경로상의 변수 라는 뜻
        return id;
    }

            // 수정
    @PutMapping("/api/noticeboards/{id}")      // .api/memos/{id} 로 Update 요청이 들어 왔을때 아래의 메소드를 실행해라 라는 뜻
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        boardService.update(id,requestDto);      //Delete 와 동일
        return id;
    }


}

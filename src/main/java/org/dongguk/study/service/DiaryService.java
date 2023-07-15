package org.dongguk.study.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dongguk.study.dto.DiaryDto;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiaryService {
    public DiaryDto getDiaryById(Long id) {
        DiaryDto diaryDto = DiaryDto.builder()
                .id(0L)
                .title("diary")
                .content("내용 입력")
                .date("2023-07-13").build();
        return diaryDto;
    }

    public DiaryDto createDiary() {    // 다이어리 생성
        DiaryDto diaryDto = DiaryDto.builder()
                .id(0L)
                .title("diary")
                .content("내용 입력")
                .date("2023-07-13").build();
        return diaryDto;
    }

//    public DiaryDto updateDiary(Long id, DiaryDto diaryDto) {   // 다이어리 수정
//
//    }

//    public void deleteDiary(Long id) {  // 다이어리 삭제
//
//
//    }
}

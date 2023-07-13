package org.dongguk.study.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dongguk.study.dto.DiaryDto;
import org.dongguk.study.service.DiaryService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class DiaryController {
    private DiaryService diaryService;

    @GetMapping("/diary/{id}")
    public DiaryDto getDiaryById(Long id) { // 다이어리 조회
        return diaryService.getDiaryById(id);
    }

    @PostMapping("/diary")    // 저장
    public DiaryDto createDiary(DiaryDto diaryDto) {
        return diaryService.createDiary(diaryDto);
    }

    @PutMapping("/diary/{id}")   // 전체 변경
    public DiaryDto updateDiary(Long id, DiaryDto diaryDto) {
        return diaryService.updateDiary(id, diaryDto);
    }

    @DeleteMapping("/diary/{id}")   // 삭제
    public void DeleteDiary(Long id) {
        diaryService.deleteDiary(id);
    }
}

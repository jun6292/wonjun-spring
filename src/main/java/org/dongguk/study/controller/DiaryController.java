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
    private final DiaryService diaryService;

    @GetMapping("/diary/{diaryId}")
    public DiaryDto getDiaryById(@PathVariable Long diaryId) { // 다이어리 조회
        return diaryService.getDiaryById(diaryId);
    }

    @PostMapping("/diary")    // 저장
    public DiaryDto createDiary(DiaryDto diaryDto) {
        return diaryService.createDiary(diaryDto.getName(), diaryDto.getTitle(), diaryDto.getContent());
    }

    @PutMapping("/diary/update/{diaryId}")   // 전체 변경
    public DiaryDto updateDiary(@PathVariable Long diaryId, String title, String content) {
        return diaryService.updateDiary(diaryId, title, content);
    }

    @DeleteMapping("/diary/delete/{diaryId}")   // 삭제
    public DiaryDto deleteDiary(@PathVariable Long diaryId) {
        return diaryService.deleteDiary(diaryId);
    }
}

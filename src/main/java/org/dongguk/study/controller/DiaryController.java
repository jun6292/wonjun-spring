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

    @PostMapping("/diary")    // 저장
    public DiaryDto createDiary(@RequestBody DiaryDto diaryDto) {
        return diaryService.createDiary(diaryDto.getName(), diaryDto.getTitle(), diaryDto.getContent());
    }

    @GetMapping("/diary/{diaryId}")
    public DiaryDto readDiary(@PathVariable("diaryId") Long diaryId) { // 다이어리 조회
        return diaryService.readDiary(diaryId);
    }

    @PutMapping("/diary/{diaryId}")   // 전체 변경
    public DiaryDto updateDiary(@PathVariable("diaryId") Long diaryId, String title, String content) {
        return diaryService.updateDiary(diaryId, title, content);
    }

    @DeleteMapping("/diary/{diaryId}")   // 삭제
    public Boolean deleteDiary(@PathVariable("diaryId") Long diaryId) {
        return diaryService.deleteDiary(diaryId);
    }
}

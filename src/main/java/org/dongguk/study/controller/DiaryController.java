package org.dongguk.study.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dongguk.study.domain.Diary;
import org.dongguk.study.dto.DiaryDto;
import org.dongguk.study.dto.UserDto;
import org.dongguk.study.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class DiaryController {
    @Autowired
    private DiaryService diaryService;

    @GetMapping("/diary/{diaryId}") // 다이어리 조회
    public DiaryDto readDiary(@PathVariable Long diaryId) {
        return diaryService.readDiary(diaryId); // 서비스에 위임
    }

    @GetMapping("/diary")   // 다이어리 목록 조회
    public List<DiaryDto> readDiaryList() {
        return diaryService.readDiaryList();    // 서비스에 위임
    }

    @PostMapping("/diary/{userId}")    // 다이어리 생성
    public DiaryDto createDiary(@RequestBody DiaryDto diaryDto, @PathVariable Long userId) {
        return diaryService.createDiary(diaryDto, userId); // 서비스에 위임
    }
    

    @PatchMapping("/diary/{diaryId}")   // 다이어리 수정
    public DiaryDto updateDiary(@PathVariable("diaryId") Long diaryId, @RequestBody DiaryDto dto) {
        return diaryService.updateDiary(diaryId, dto);  // 서비스에 위임
    }

    @DeleteMapping("/diary/{diaryId}")   // 삭제
    public Boolean deleteDiary(@PathVariable Long diaryId) {
        return diaryService.deleteDiary(diaryId);   // 서비스에 위임
    }
}

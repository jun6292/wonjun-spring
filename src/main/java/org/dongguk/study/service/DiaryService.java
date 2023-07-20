package org.dongguk.study.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dongguk.study.Repository.DiaryRepository;
import org.dongguk.study.domain.Diary;
import org.dongguk.study.dto.DiaryDto;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;
    public DiaryDto getDiaryById(Long diaryId) {     // 다이어리 조회
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> new RuntimeException("다이어리가 존재하지 않습니다."));
        DiaryDto diaryDto = DiaryDto.builder()
                .diaryId(diary.getDiaryId())
                .name(diary.getName())
                .title(diary.getTitle())
                .content(diary.getContent())
                .createdDate(diary.getCreatedDate())
                .build();
        return diaryDto;
    }

    public DiaryDto createDiary(String name, String title, String content) {    // 다이어리 생성
        Diary diary = diaryRepository.save(Diary.builder()
                .name(name)
                .title(title)
                .content(content)
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .build());

        DiaryDto diaryDto = DiaryDto.builder()
                .diaryId(diary.getDiaryId())
                .name(diary.getName())
                .title(diary.getTitle())
                .content(diary.getContent())
                .createdDate(diary.getCreatedDate())
                .build();
        return diaryDto;
    }

    public DiaryDto updateDiary(Long diaryId, String title, String content) {   // 다이어리 수정
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> new RuntimeException("다이어리가 존재하지 않습니다."));
        diary.setTitle(title);
        diary.setContent(content);
        diaryRepository.save(diary);
        DiaryDto diaryDto = DiaryDto.builder()
                .diaryId(diary.getDiaryId())
                .name(diary.getName())
                .title(diary.getTitle())
                .content(diary.getContent())
                .createdDate(diary.getCreatedDate())
                .build();
        return diaryDto;
    }
    @Transactional
    public DiaryDto deleteDiary(Long diaryId) {  // 다이어리 삭제
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> new RuntimeException("다이어리가 존재하지 않습니다."));
        diaryRepository.save(diary);
        DiaryDto diaryDto = DiaryDto.builder()
                .diaryId(diary.getDiaryId())
                .name(diary.getName())
                .title(diary.getTitle())
                .content(diary.getContent())
                .createdDate(diary.getCreatedDate())
                .build();
        return diaryDto;
    }
}

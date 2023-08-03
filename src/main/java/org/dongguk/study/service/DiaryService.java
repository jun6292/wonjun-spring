package org.dongguk.study.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dongguk.study.Repository.DiaryRepository;
import org.dongguk.study.Repository.DiaryTagMapRepository;
import org.dongguk.study.Repository.DiaryTagRepository;
import org.dongguk.study.Repository.UserRepository;
import org.dongguk.study.domain.Diary;
import org.dongguk.study.domain.DiaryTag;
import org.dongguk.study.domain.User;
import org.dongguk.study.dto.DiaryDto;
import org.dongguk.study.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final DiaryTagRepository diaryTagRepository;
    private final DiaryTagMapRepository diaryTagMapRepository;
    private final UserRepository userRepository;

    // 아직 구현중
    public DiaryDto createDiary(DiaryDto diaryDto, UserDto userDto) {    // 다이어리 생성
        User user = userRepository.findById(userDto.getId()).orElseThrow(() -> new RuntimeException("유저가 존재하지 않습니다."));
        List<String> DiaryTags = diaryDto.getTags();
        Diary diary = diaryRepository.save(Diary.builder()
                .name(user.getName())
                .title(diaryDto.getTitle())
                .content(diaryDto.getContent())
                .build());
        DiaryTag diaryTag = diaryTagRepository.save(DiaryTags.builder()
                .name(diaryDto.getTags()))
                .build());

        DiaryDto diaryDto = DiaryDto.builder()
                .id(diary.getId())
                .name(diary.getName())
                .title(diary.getTitle())
                .content(diary.getContent())
                .createdDate(diary.getCreatedDate())
                .tags(diaryTag.getName())
                .build();
        return diaryDto;
    }
    public DiaryDto readDiary(Long diaryId) {     // 다이어리 조회
        Diary diary = diaryRepository.findByIdAndIsVisible(diaryId, true).orElseThrow(() -> new RuntimeException("다이어리가 존재하지 않습니다."));
        List<String> diaryTags = diaryTagMapRepository.findTagsByDiaryId(diaryId);
        DiaryDto diaryDto = DiaryDto.builder()
                .id(diary.getId())
                .name(diary.getName())
                .title(diary.getTitle())
                .content(diary.getContent())
                .createdDate(diary.getCreatedDate())
                .tags(diaryTags)
                .build();
        return diaryDto;
    }

    public List<Diary> readDiaryList() {     // 다이어리 목록 조회
        List<Diary> diaryList = diaryRepository.findAll();
        return diaryList;
    }

    // 아직 구현중
    @Transactional
    public DiaryDto updateDiary(Long diaryId, String title, String content) {   // 다이어리 수정
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> new RuntimeException("다이어리가 존재하지 않습니다."));
        diary.update(title, content);
        DiaryDto diaryDto = DiaryDto.builder()
                .id(diary.getId())
                .name(diary.getName())
                .title(diary.getTitle())
                .content(diary.getContent())
                .createdDate(diary.getCreatedDate())
                .build();
        return diaryDto;
    }
    @Transactional
    public Boolean deleteDiary(Long diaryId) {  // 다이어리 삭제
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> new RuntimeException("다이어리가 존재하지 않습니다."));
        diary.delete();
        return Boolean.TRUE;
    }
}

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
import org.dongguk.study.domain.DiaryTagMap;
import org.dongguk.study.domain.User;
import org.dongguk.study.dto.DiaryDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final DiaryTagRepository diaryTagRepository;
    private final DiaryTagMapRepository diaryTagMapRepository;
    private final UserRepository userRepository;

    @Transactional
    public DiaryDto createDiary(DiaryDto diaryDto, Long userId) {    // 다이어리 생성
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저가 존재하지 않습니다."));
        Diary diary = Diary.builder()   // 다이어리 엔티티 생성
                .title(diaryDto.getTitle())
                .content(diaryDto.getContent())
                .name(user.getName())
                .user(user)
                .build();
        List<String> tags = diaryDto.getTags(); // 입력 받은 태그들 가져옴.

        List<DiaryTag> tagList = new ArrayList<>();    // 다이어리 태그 엔티티 생성
        for (String name : tags) {
            DiaryTag tagName = diaryTagRepository.findByDiaryTagName(name).orElse(null);
            if (tagName == null) {
                DiaryTag diaryTag = tagName.builder()
                        .name(name)
                        .build();
                diaryTagRepository.save(diaryTag);
                tagList.add(diaryTag);
            }
            else {
                tagList.add(tagName);
            }
        }

        List<DiaryTagMap> diaryTagMap = new ArrayList<>();
        for (DiaryTag diaryTag : tagList) {
            DiaryTagMap map = DiaryTagMap.builder()
                    .diary(diary)
                    .diaryTag(diaryTag)
                    .build();
            diaryTagMap.add(map);
            diary.getDiaryTagMaps().add(map);
            diaryTag.getDiaryTagMaps().add(map);
        }
        diaryRepository.save(diary);    // 태그가 붙은 다이어리 DB에 저장
        DiaryDto saved = DiaryDto.builder() // DTO로 변환 후 반환
                .id(diary.getId())
                .userId(diary.getUser().getId())
                .name(diary.getName())
                .title(diary.getTitle())
                .content(diary.getContent())
                .createdDate(diary.getCreatedDate())
                .visible(diary.isVisible())
                .tags(diaryTagMapRepository.findTagsByDiaryId(diary.getId()))
                .build();
        return saved;
    }

    public DiaryDto readDiary(Long diaryId) {     // 다이어리 조회
        Diary diary = diaryRepository.findByIdAndIsVisible(diaryId, true).orElseThrow(() -> new RuntimeException("다이어리가 존재하지 않습니다."));
        List<String> diaryTags = diaryTagMapRepository.findTagsByDiaryId(diaryId);
        DiaryDto show = DiaryDto.builder()
                .id(diary.getId())
                .userId(diary.getUser().getId())
                .name(diary.getName())
                .title(diary.getTitle())
                .content(diary.getContent())
                .createdDate(diary.getCreatedDate())
                .visible(diary.isVisible())
                .tags(diaryTags)
                .build();
        return show;
    }

    public List<DiaryDto> readDiaryList() {     // 전체 다이어리 조회
        List<Diary> diaryList = diaryRepository.findAll();
        List<DiaryDto> diaryDtos = new ArrayList<>();
        for (Diary diary : diaryList) {
            DiaryDto diaryDto = DiaryDto.builder()
                    .id(diary.getId())
                    .userId(diary.getUser().getId())
                    .title(diary.getTitle())
                    .name(diary.getName())
                    .createdDate(diary.getCreatedDate())
                    .visible(diary.isVisible())
                    .build();
            diaryDtos.add(diaryDto);
        }
        return diaryDtos;
    }

    @Transactional
    public DiaryDto updateDiary(Long diaryId, DiaryDto dto) {   // 다이어리 수정
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> new RuntimeException("다이어리가 존재하지 않습니다."));
        diary.update(dto);
        List<String> tags = dto.getTags(); // 입력 받은 태그들 가져옴.

        // 매핑 싹다 삭제하고 태그 다시 달기
        List<DiaryTagMap> diaryTagMaps = diaryTagMapRepository.findAllByDiary(diary);
        diaryTagMapRepository.deleteAll(diaryTagMaps);

        List<DiaryTag> tagList = new ArrayList<>();    // 다이어리 태그 엔티티 생성
        for (String name : tags) {
            DiaryTag tagName = diaryTagRepository.findByDiaryTagName(name).orElse(null);
            if (tagName == null) {
                DiaryTag diaryTag = tagName.builder()
                        .name(name)
                        .build();
                diaryTagRepository.save(diaryTag);
                tagList.add(diaryTag);
            }
            else {
                tagList.add(tagName);
            }
        }

        List<DiaryTagMap> diaryTagMap = new ArrayList<>();
        for (DiaryTag diaryTag : tagList) {
            DiaryTagMap map = DiaryTagMap.builder()
                    .diary(diary)
                    .diaryTag(diaryTag)
                    .build();
            diaryTagMap.add(map);
            diary.getDiaryTagMaps().add(map);
            diaryTag.getDiaryTagMaps().add(map);
        }

        diaryRepository.save(diary);    // 태그가 붙은 다이어리 DB에 저장
        DiaryDto updated = DiaryDto.builder()
                .id(diary.getId())
                .userId(diary.getUser().getId())
                .name(diary.getName())
                .title(diary.getTitle())
                .content(diary.getContent())
                .createdDate(diary.getCreatedDate())
                .visible(diary.isVisible())
                .tags(diaryTagMapRepository.findTagsByDiaryId(diary.getId()))
                .build();
        return updated;
    }

    @Transactional
    public Boolean deleteDiary(Long diaryId) {  // 다이어리 삭제
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> new RuntimeException("다이어리가 존재하지 않습니다."));
        diary.delete();    // DB에서는 삭제 안함.
        return Boolean.TRUE;
    }
}

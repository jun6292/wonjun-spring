package org.dongguk.study.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Getter @Setter
@RequiredArgsConstructor
public class DiaryDto {
    private Long diaryId;    // 다이어리를 식별하기 위한 id
    private String name;    // 다이어리 작성자
    private String title;   // 다이어리 제목
    private String content; // 다이어리 내용
    private Timestamp createdDate;    // 다이어리 생성 날짜

    // 다이어리 id, 제목, 내용
    @Builder
    public DiaryDto(Long diaryId, String name, String title, String content, Timestamp createdDate) {
        this.diaryId = diaryId;
        this.name = name;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }
}

package org.dongguk.study.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

@Getter @Setter
@RequiredArgsConstructor
public class DiaryDto {
    private Long id;    // 다이어리를 식별하기 위한 id
    private String title;   // 다이어리 제목
    private String content; // 다이어리 내용
    private String date;    // 다이어리 날짜

    // 다이어리 id, 제목, 내용
    @Builder
    public DiaryDto(Long id, String title, String content, String date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
    }
}

package org.dongguk.study.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
import org.dongguk.study.domain.DiaryTag;

import java.sql.Timestamp;
import java.util.List;

@Getter @Setter
@RequiredArgsConstructor
public class DiaryDto {
    private Long id;    // 다이어리를 식별하기 위한 id
    private String name;    // 다이어리 작성자
    private String title;   // 다이어리 제목
    private String content; // 다이어리 내용
    private Timestamp createdDate;    // 다이어리 생성 날짜
    private List<String> tags;    // 다이어리 태그
    private Boolean visible;

    // 다이어리 id, 제목, 내용
    @Builder
    public DiaryDto(Long id, String name, String title, String content, Timestamp createdDate, boolean visible, List<String> tags) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.visible = visible;
        this.tags = tags;
    }

    @Builder
    public DiaryDto(String title, String content, List<String> tags) {  // update 생성자
        this.title = title;
        this.content = content;
        this.tags = tags;
    }
}

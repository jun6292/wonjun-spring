package org.dongguk.study.dto;

import lombok.*;
import org.dongguk.study.domain.Diary;
import org.dongguk.study.domain.User;

import java.sql.Timestamp;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class DiaryDto {
    private Long id;    // 다이어리를 식별하기 위한 id
    private Long userId;    // 유저 정보
    private String name;    // 유저 이름
    private String title;   // 다이어리 제목
    private String content; // 다이어리 내용
    private Timestamp createdDate;    // 다이어리 생성 날짜
    private List<String> tags;    // 다이어리 태그
    private Boolean visible;

    @Builder
    public DiaryDto(Long id, Long userId, String name, String title, String content,
                    Timestamp createdDate, boolean visible, List<String> tags) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.visible = visible;
        this.tags = tags;
    }
}

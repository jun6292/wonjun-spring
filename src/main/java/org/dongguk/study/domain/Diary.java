package org.dongguk.study.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dongguk.study.dto.DiaryDto;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "diaries")
@DynamicUpdate
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_date", nullable = false)
    private Timestamp createdDate;

    @Column(name = "visible", nullable = false)
    private boolean visible;

    @JoinColumn(name = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "diary")
    private List<DiaryTagMap> diaryTagMaps;

    @Builder
    public Diary(String name, String title, String content, User user, List<DiaryTagMap> diaryTagMaps) {
        this.name = name;
        this.title = title;
        this.content = content;
        this.createdDate = Timestamp.valueOf(LocalDateTime.now());
        this.visible = true;
        this.user = user;
        this.diaryTagMaps = diaryTagMaps;
    }

    public void update(DiaryDto dto) {
        if (dto.getTitle() != null)
            this.title = title;
        if (dto.getContent() != null)
            this.content = content;
    }

    public void delete() {
        this.visible = false;
    }
}

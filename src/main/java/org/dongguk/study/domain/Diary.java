package org.dongguk.study.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "diary")
@DynamicUpdate
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    private Long diaryId;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_date", nullable = false)
    private Timestamp createdDate;

    @Column(name = "is_visible", nullable = false)
    private boolean isVisible;

    @Builder
    public Diary(String name, String title, String content) {
        this.name = name;
        this.title = title;
        this.content = content;
        this.isVisible = true;
        this.createdDate = Timestamp.valueOf(LocalDateTime.now());
    }
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
    public void delete() {
        this.isVisible = false;
    }

}

package org.dongguk.study.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "diary_tag_map")
public class DiaryTagMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_tag_map_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    @Column(name = "tag_id", nullable = false)
    private DiaryTag diaryTag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    @Column(name = "diary_id", nullable = false)
    private Diary diary;

    @Builder
    public DiaryTagMap(Long id, Diary diary, DiaryTag diaryTag) {
        this.id = id;
        this.diary = diary;
        this.diaryTag = diaryTag;
    }
}

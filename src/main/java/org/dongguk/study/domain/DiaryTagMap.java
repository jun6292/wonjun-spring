package org.dongguk.study.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
    @Column(name = "diary_tag_map_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    @Column(name = "tag_id", nullable = false)
    private DiaryTag diaryTag;

    @ManyToOne
    @JoinColumn(name = "diary_id")
    @Column(name = "diary_id", nullable = false)
    private Diary diary;

}

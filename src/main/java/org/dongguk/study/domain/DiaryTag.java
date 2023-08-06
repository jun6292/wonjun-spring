package org.dongguk.study.domain;

import jakarta.persistence.*;
import lombok.*;
import org.dongguk.study.dto.DiaryDto;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Getter @ToString
@Entity
@Table(name = "dairy_tag")
@NoArgsConstructor
@DynamicUpdate
public class DiaryTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    @Column(name = "tag_name", nullable = false)
    private String name;

    @Column
    @OneToMany(mappedBy = "diary_tag")
    private List<DiaryTagMap> diaryTagMaps;

    @Builder
    public DiaryTag(String name, List<DiaryTagMap> diaryTagMaps) {
        this.name = name;
        this.diaryTagMaps = diaryTagMaps;
    }
}

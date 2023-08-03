package org.dongguk.study.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Getter @ToString
@Entity
@Table(name = "dairy_tag")
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class DiaryTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    @Column(name = "tag_name", nullable = false)
    private String name;
}

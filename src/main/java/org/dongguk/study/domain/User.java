package org.dongguk.study.domain;

import jakarta.persistence.*;
import lombok.*;
import org.dongguk.study.dto.UserDto;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "created_date", nullable = false)
    private Timestamp createdDate;

    @Builder
    public User(String name, Timestamp createdDate) {
        this.name = name;
        this.createdDate = createdDate;
    }

    public void patch(UserDto dto) {
        if (dto.getId() != this.id)
            throw new IllegalArgumentException("수정 실패! 잘못된 id가 입력됐습니다.");
        if (dto.getName() != null)
            this.name = dto.getName();
    }
}

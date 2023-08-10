package org.dongguk.study.domain;

import jakarta.persistence.*;
import lombok.*;
import org.dongguk.study.dto.UserDto;
import org.dongguk.study.util.ELoginProvider;
import org.dongguk.study.util.EUserRole;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "social_id", nullable = false)
    private String socialId;

    @Column(name = "provider", nullable = false)
    @Enumerated(EnumType.STRING)
    private ELoginProvider provider;

    @Column(name = "role", nullable = false)
    private EUserRole role;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "created_date", nullable = false)
    private Timestamp createdDate;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Builder
    public User(String socialId, ELoginProvider provider,
                EUserRole role, String name) {
        this.socialId = socialId;
        this.provider = provider;
        this.name = name;
        this.role = role;
        this.createdDate = Timestamp.valueOf(LocalDateTime.now());
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void patch(UserDto dto) {
        if (dto.getId() != this.id)
            throw new IllegalArgumentException("수정 실패! 잘못된 id가 입력됐습니다.");
        if (dto.getName() != null)
            this.name = dto.getName();
    }
}

package com.chapssal_tteok.preview.domain.user.entity;

import com.chapssal_tteok.preview.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id; // 사용자 ID

    @Column(length = 20, nullable = false, unique = true)
    private String username; // 사용자명

    @Column(length = 20)
    private String name; // 이름

    @Column(length = 255, nullable = false, unique = true)
    private String email; // 이메일

    @Column(length = 255)
    private String password; // 비밀번호

    @Column(nullable = false)
    private Boolean active = true; // 활성화 여부 (기본값 true)

    private LocalDateTime deactivatedAt; // 비활성화 시간
}

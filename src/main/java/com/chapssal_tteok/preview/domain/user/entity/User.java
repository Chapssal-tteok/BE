package com.chapssal_tteok.preview.domain.user.entity;

import com.chapssal_tteok.preview.global.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // 역할 (USER, ADMIN)

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

    public User updateRole(Role newRole) {
        this.role = newRole;
        return this;
    }

    public void setName(@Size(min = 2, max = 20, message = "이름은 2~20자 사이여야 합니다.") String name) {
        this.name = name;
    }

    public void setUsername(@Size(min = 2, max = 20, message = "사용자명은 2~20자 사이여야 합니다.") String username) {
        this.username = username;
    }

    public void setPassword(String encode) {
        this.password = encode;
    }
}

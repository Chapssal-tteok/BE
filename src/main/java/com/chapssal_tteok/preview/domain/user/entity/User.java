package com.chapssal_tteok.preview.domain.user.entity;

import com.chapssal_tteok.preview.domain.interview.entity.Interview;
import com.chapssal_tteok.preview.domain.resume.entity.Resume;
import com.chapssal_tteok.preview.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @Builder.Default
    private Boolean active = true; // 활성화 여부 (기본값 true)

    private LocalDateTime deactivatedAt; // 비활성화 시간

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Resume> resumes = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Interview> interviews = new ArrayList<>();

    public boolean updateRole(Role newRole) {
        if (newRole != null && !newRole.equals(this.role)) {
            this.role = newRole;
            return true;
        }
        return false;
    }

    public boolean updateName(String newName) {
        if (newName != null && !newName.equals(this.name)) {
            this.name = newName;
            return true;
        }
        return false;
    }

    public boolean updateEmail(String newEmail) {
        if (newEmail != null && !newEmail.equals(this.email)) {
            this.email = newEmail;
            return true;
        }
        return false;
    }

    public boolean updatePassword(String newEncodedPassword) {
        if (newEncodedPassword != null && !newEncodedPassword.equals(this.password)) {
            this.password = newEncodedPassword;
            return true;
        }
        return false;
    }
}

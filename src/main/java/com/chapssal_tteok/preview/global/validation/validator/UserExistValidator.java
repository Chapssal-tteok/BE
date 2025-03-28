package com.chapssal_tteok.preview.global.validation.validator;

import com.chapssal_tteok.preview.domain.user.repository.UserRepository;
import com.chapssal_tteok.preview.global.validation.annotation.ExistUser;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserExistValidator implements ConstraintValidator<ExistUser, Long> {

    private final UserRepository userRepository;

    @Override
    public void initialize(ExistUser constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long userId, ConstraintValidatorContext context) {
        if (userId == null) {
            return true; // null은 검증하지 않음
        }

        boolean isValid = userRepository.existsById(userId);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("USER_NOT_FOUND")
                    .addConstraintViolation();
        }

        return isValid;
    }
}

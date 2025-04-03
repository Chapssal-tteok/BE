package com.chapssal_tteok.preview.global.apiPayload.code.status;

import com.chapssal_tteok.preview.global.apiPayload.code.BaseErrorCode;
import com.chapssal_tteok.preview.global.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
    _NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404", "찾을 수 없습니다."),

    // 사용자 관련 에러
    // 400 Bad Request
    NO_CHANGE_DETECTED(HttpStatus.BAD_REQUEST, "USER4001", "변경된 내용이 없습니다."), // 변경된 내용이 없음
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "USER4002", "비밀번호가 유효하지 않습니다."), // 비밀번호 유효성 검사 실패
    // 401 Unauthorized
    PASSWORD_NOT_CORRESPOND (HttpStatus.UNAUTHORIZED, "USER4101", "비밀번호가 일치하지 않습니다."), // 인증 실패
    USER_NOT_LOGGED_IN(HttpStatus.UNAUTHORIZED, "USER4102", "로그인이 필요합니다. 다시 로그인해주세요."),
    // 403 Forbidden
    USER_NOT_AUTHORIZED (HttpStatus.FORBIDDEN, "USER4301", "사용자 권한이 없습니다."), // 권한 부족
    USER_NOT_MATCH (HttpStatus.FORBIDDEN, "USER4302", "사용자가 일치하지 않습니다."), // 권한 부족
    // 404 Not Found
    USER_NOT_FOUND (HttpStatus.NOT_FOUND, "USER4401", "사용자가 존재하지 않습니다."), // 존재하지 않는 사용자
    // 409 Conflict
    USER_ALREADY_EXIST (HttpStatus.CONFLICT, "USER4901", "이미 존재하는 사용자입니다."), // 리소스 충돌 (회원가입 시)
    EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "USER4902","이미 존재하는 이메일입니다"), // 리소스 충돌
    USERNAME_ALREADY_EXISTS(HttpStatus.CONFLICT, "USER4903", "이미 존재하는 사용자명입니다."), // 리소스 충돌

    // 토큰 관련 에러
    EXPIRED_TOKEN(HttpStatus.BAD_REQUEST, "TOKEN4001", "토큰이 만료되었습니다."),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "TOKEN4002", "유효하지 않은 토큰입니다."),
    UNSUPPORTED_TOKEN(HttpStatus.BAD_REQUEST, "TOKEN4003", "지원하지 않는 토큰입니다."),
    EMPTY_TOKEN(HttpStatus.BAD_REQUEST, "TOKEN4004", "토큰이 비어있습니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "TOKEN4005", "유효하지 않은 리프레시 토큰입니다."),

    // 자기소개서 관련 에러
    RESUME_NOT_FOUND(HttpStatus.NOT_FOUND, "RESUME4401", "자기소개서가 존재하지 않습니다."),

    // 자기소개서 문답 관련 에러
    RESUME_QA_NOT_FOUND(HttpStatus.NOT_FOUND, "RESUMEQA4401", "자기소개서 문답이 존재하지 않습니다."),

    // 면접 관련 에러
    INTERVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "INTERVIEW4401", "면접이 존재하지 않습니다."),

    // 면접 문답 관련 에러
    INTERVIEW_QA_NOT_FOUND(HttpStatus.NOT_FOUND, "INTERVIEWQA4401", "면접 문답이 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}

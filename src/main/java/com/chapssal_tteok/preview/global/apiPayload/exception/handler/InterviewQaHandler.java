package com.chapssal_tteok.preview.global.apiPayload.exception.handler;

import com.chapssal_tteok.preview.global.apiPayload.code.BaseErrorCode;
import com.chapssal_tteok.preview.global.apiPayload.exception.GeneralException;

public class InterviewQaHandler extends GeneralException {

    public InterviewQaHandler(BaseErrorCode errorCode) {
      super(errorCode);
    }
}

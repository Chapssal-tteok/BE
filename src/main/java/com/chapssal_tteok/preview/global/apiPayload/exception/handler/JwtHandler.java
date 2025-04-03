package com.chapssal_tteok.preview.global.apiPayload.exception.handler;

import com.chapssal_tteok.preview.global.apiPayload.code.BaseErrorCode;
import com.chapssal_tteok.preview.global.apiPayload.exception.GeneralException;

public class JwtHandler extends GeneralException {
  public JwtHandler(BaseErrorCode errorCode) {
    super(errorCode);
  }
}

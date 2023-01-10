package com.green.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BoardStatus {

        정상("정상"),
        삭제("삭제"),
        비홣성화("비활성화");

        private final String boardStatus;

}

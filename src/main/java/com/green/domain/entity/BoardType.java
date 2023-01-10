package com.green.domain.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BoardType {
    공지사항("공지사항"),
    자유게시판("자유게시판");

    private final String BoardType;
}

package com.fastcampus.projectboard.dto;

import java.time.LocalDateTime;

public record ArticleCommentDto(
        LocalDateTime createAt,
        String createBy,
        LocalDateTime modifiedAt,
        String modifiedBy,
        String content
) {
    public static ArticleCommentDto of(LocalDateTime createAt, String createBy, LocalDateTime modifiedAt, String modifiedBy, String content) {
        return new ArticleCommentDto(createAt, createBy, modifiedAt, modifiedBy, content);
    }
}
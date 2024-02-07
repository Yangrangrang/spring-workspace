package com.fastcampus.projectboard.dto;

import com.fastcampus.projectboard.domain.Article;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 게시글을 가져오면 해당 게시글에 해당하는 댓글도 같이 가져 올 수 있도록
 * 양방향 바인딩을 했는데 ArticleDto는 해당 기능을 사용할 수 없는 상태로 되어있음
 * 그래서 DTO가 Article과 ArticleComment 둘 다 가지고 있는 것도 하나 만들면 편하겠다는 생각에서
 * 해당 DTO를 만듦
 * 구조가 ArticleDto와 비슷한데, Set<ArticleCommentDto> articleCommentDtos 가 도메인 코드에 있던 것 그대로 가져온다.
 * @param id
 * @param userAccountDto
 * @param articleCommentDtos
 * @param title
 * @param content
 * @param hashtag
 * @param createdAt
 * @param createdBy
 * @param modifiedAt
 * @param modifiedBy
 */
public record ArticleWithCommentsDto(
        Long id,
        UserAccountDto userAccountDto,
        Set<ArticleCommentDto> articleCommentDtos,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static ArticleWithCommentsDto of(Long id, UserAccountDto userAccountDto, Set<ArticleCommentDto> articleCommentDtos, String title, String content, String hashtag, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ArticleWithCommentsDto(id, userAccountDto, articleCommentDtos, title, content, hashtag, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static ArticleWithCommentsDto from(Article entity) {
        return new ArticleWithCommentsDto(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getArticleComments().stream()
                        .map(ArticleCommentDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }
}

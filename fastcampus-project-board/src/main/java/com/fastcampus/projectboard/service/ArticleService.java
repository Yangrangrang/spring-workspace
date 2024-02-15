package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.type.SearchType;
import com.fastcampus.projectboard.dto.ArticleDto;
import com.fastcampus.projectboard.dto.ArticleWithCommentsDto;
import com.fastcampus.projectboard.repository.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor    // 필수 필드에 대한 생성자를 자동으로 만들어줌
@Transactional
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
        // 검색어가 없을 경우
        if (searchKeyword == null || searchKeyword.isBlank()) {
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }

        // 검색어가 있을 경우 (searchType이 enum) 검색쿼리를 따로
        // switch가 스스로 return 할 수 있다..........
        return switch (searchType) {
            case TITLE -> articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from);
            case ID ->
                    articleRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(ArticleDto::from);
            case NICKNAME ->
                    articleRepository.findByUserAccount_NicknameContaining(searchKeyword, pageable).map(ArticleDto::from);
            case HASHTAG -> articleRepository.findByHashtag("#" + searchKeyword, pageable).map(ArticleDto::from);
        };
    }

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticle(long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleWithCommentsDto::from)  // 치환을 해주지만 optional이기 떄문에 까줘야함.
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId: " + articleId));
    }

    // 클래스 레벨 트랜잭션에 의해서 트랜잭션에 감싸져 있는 효과를 가지고 있다.
    public void saveArticle(ArticleDto dto) {
        // dto를 넣되, toEntity (dto정보로 부터 Entity를 하나 만들어서) save
        articleRepository.save(dto.toEntity());
    }

    // getReferenceById : findById랑 비슷한 역할을 하지만 내부 동작이 약간 다름.
    // (단건 조회 > findById)
    // 데이터가 꼭 필요하지 않아도 select 쿼리가 날라감
    // 수정을 해야하는 상황 (엔티티가 아니라 DTO 이기 때문에 해당 데이터가 실제로 있었는지 DB확인 필요)
    public void updateArticle(ArticleDto dto) {
        try {
            Article article = articleRepository.getReferenceById(dto.id());
            if (dto.title() != null) { article.setTitle(dto.title());}
            if (dto.content() != null) { article.setContent(dto.content());}
            article.setHashtag(dto.hashtag());
        } catch (EntityNotFoundException e) {
            log.warn("게시글 없데이트 실패. 게시글을 찾을 수 없습니다. - dto : {}", dto);
        }
    }

    public void deleteArticle(long articleId) {
        articleRepository.deleteById(articleId);
    }

    public long getArticleCount() {
        return articleRepository.count();
    }
}

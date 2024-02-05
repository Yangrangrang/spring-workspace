package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.type.SearchType;
import com.fastcampus.projectboard.dto.ArticleDto;
import com.fastcampus.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/*
spring boot가 띄워지지 않게끔 테스트
테스트는 최대한 무겁지 않은게 좋음.
추가적으로 필요한 디펜던시가 있다면 모킹을 하는 방식으로 접근 : 많이 사용되는 라이브러리 Mockito
 */
@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks
    private  ArticleService sut;
    @Mock
    private ArticleRepository articleRepository;

    @DisplayName("게시글을 검색하면, 게시글 리스트를 반환한다.")
    @Test
    void givenSearchParameter_whenSearchingArticles_thenReturnArticleList() {
        //given

        //when
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword");   // 제목, 본문, ID, 닉네임, 해시태그 (검색)

        //then
        assertThat(articles).isNotNull();
    }

    @DisplayName("게시글을 조회하면, 게시글을 반환한다.")
    @Test
    void givenArticleId_whenSearchingArticle_thenReturnsArticle() {
        //given

        //when
        ArticleDto articles = sut.searchArticle(1L);

        //then
        assertThat(articles).isNotNull();
    }

}
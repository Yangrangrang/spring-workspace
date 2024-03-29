package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.ArticleComment;
import com.fastcampus.projectboard.domain.Hashtag;
import com.fastcampus.projectboard.domain.UserAccount;
import com.fastcampus.projectboard.dto.ArticleCommentDto;
import com.fastcampus.projectboard.dto.UserAccountDto;
import com.fastcampus.projectboard.repository.ArticleCommentRepository;
import com.fastcampus.projectboard.repository.ArticleRepository;
import com.fastcampus.projectboard.repository.UserAccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks private ArticleCommentService sut;

    @Mock
    private ArticleCommentRepository articleCommentRepository;

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private UserAccountRepository userAccountRepository;

    @DisplayName("게시글ID로 조회하면, 해당하는 댓글 리스트를 반환한다.")
    @Test
    void givenArticleId_whenSearchingComments_thenReturnComments() {
        //given
        Long articleId = 1L;
        ArticleComment expectedParentComment = createArticleComment(1L, "parent content");
        ArticleComment expectedChildComment = createArticleComment(2L, "child content");
        expectedChildComment.setParentCommentId(expectedParentComment.getId());
        given(articleCommentRepository.findByArticle_Id(articleId)).willReturn(List.of(
                expectedParentComment,
                expectedChildComment
        ));

        //when
        List<ArticleCommentDto> actual = sut.searchArticleComments(articleId);

        //then
        assertThat(actual).hasSize(2);
        assertThat(actual)
                .extracting("id", "articleId", "parentCommentId", "content")
                .containsExactlyInAnyOrder(
                        tuple(1L, 1L, null, "parent content"),
                        tuple(2L, 1L, 1L, "child content")
                );
        then(articleCommentRepository).should().findByArticle_Id(articleId);
    }

    @DisplayName("댓글 정보를 입력하면, 댓글을 저장한다.")
    @Test
    void givenArticleCommentInfo_whenSavingComment_thenSavesComment() {
        //given
        ArticleCommentDto dto = createArticleCommentDto("댓글");
        given(articleRepository.getReferenceById(dto.articleId())).willReturn(createArticle());
        given(userAccountRepository.getReferenceById(dto.userAccountDto().userId())).willReturn(createUserAccount());
        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);

        //when
        sut.saveArticleComment(dto);

        //then
        then(articleRepository).should().getReferenceById(dto.articleId());
        then(userAccountRepository).should().getReferenceById(dto.userAccountDto().userId());
        then(articleCommentRepository).should(never()).getReferenceById(anyLong());
        then(articleCommentRepository).should().save(any(ArticleComment.class));
    }

    @DisplayName("댓글 저장을 시도했는데 맞는 게시글이 없으면, 경고 로그를 찍고 아무것도 안 한다.")
    @Test
    void givenNonexistentArticle_whenSavingArticleComment_thenLogsSituationAndDoesNothing() {
        //given
        ArticleCommentDto dto = createArticleCommentDto("댓글 ");
        given(articleRepository.getReferenceById(dto.articleId())).willThrow(EntityNotFoundException.class);

        //when
        sut.saveArticleComment(dto);

        //then
        then(articleRepository).should().getReferenceById(dto.articleId());
        then(userAccountRepository).shouldHaveNoInteractions();
        then(articleCommentRepository).shouldHaveNoInteractions();
    }

//    @DisplayName("댓글 정보를 입력하면, 댓글을 수정한다.")
//    @Test
//    void givenArticleCommentInfo_whenUpdatingdArticleComment_thenUpdatesArticleCommnet() {
//        //given
//        String oldContent = "content";
//        String updateContent = "댓글";
//        ArticleComment articleComment = createArticleComment(oldContent);   // 이전 작성 댓글 객체 생성
//        ArticleCommentDto dto = createArticleCommentDto(updateContent);     // 업데이트 내용을 받아서 dto 객체 생성
//        given(articleCommentRepository.getReferenceById(dto.id())).willReturn(articleComment);
//
//        //when
//        sut.updateArticleComment(dto);
//
//        //then
//        assertThat(articleComment.getContent())
//                .isNotEqualTo(oldContent)
//                .isEqualTo(updateContent);
//        then(articleCommentRepository).should().getReferenceById(dto.id());
//    }

//    @DisplayName("없는 댓글 정보를 수정하려고 하면, 경고 로그를 찍고 아무 것도 안 한다.")
//    @Test
//    void givenNonexistentArticleCommnent_whenUpdatingArticleComment_thenLogsWarningAndDoesNothing() {
//        //given
//        ArticleCommentDto dto = createArticleCommentDto("댓글");
//        given(articleCommentRepository.getReferenceById(dto.id())).willThrow(EntityNotFoundException.class);
//
//        //when
//        sut.updateArticleComment(dto);
//
//        //then
//        then(articleCommentRepository).should().getReferenceById(dto.id());
//    }

    @DisplayName("부모 댓글 ID와 댓글 정보를 입력하면, ,대댓글을 저장한다.")
    @Test
    void givenParentCommentIdAndArticleCommentInfo_whenSaving_thenSavesChildComment() {
        //given
        Long parentCommentId = 1L;
        ArticleComment parent = createArticleComment(parentCommentId, "댓글");
        ArticleCommentDto child = createArticleCommentDto(parentCommentId, "대댓글");
        given(articleRepository.getReferenceById(child.articleId())).willReturn(createArticle());
        given(userAccountRepository.getReferenceById(child.userAccountDto().userId())).willReturn(createUserAccount());
        given(articleCommentRepository.getReferenceById(child.parentCommentId())).willReturn(parent);

        //when
        sut.saveArticleComment(child);

        //then
        assertThat(child.parentCommentId()).isNotNull();
        then(articleRepository).should().getReferenceById(child.articleId());
        then(userAccountRepository).should().getReferenceById(child.userAccountDto().userId());
        then(articleCommentRepository).should().getReferenceById(child.parentCommentId());
        then(articleCommentRepository).should(never()).save(any(ArticleComment.class));
    }

    @DisplayName("댓글 ID를 입력하면, 댓글을 삭제한다.")
    @Test
    void givenArticleCommentId_whenDeletingArticleCommnet_thenDeletesArticleComment() {
        //given
        Long ArticleCommentId = 1L;
        String userId = "uno";
        willDoNothing().given(articleCommentRepository).deleteByIdAndUserAccount_UserId(ArticleCommentId, userId);

        //when
        sut.deleteArticleComment(ArticleCommentId, userId);

        //then
        then(articleCommentRepository).should().deleteByIdAndUserAccount_UserId(ArticleCommentId, userId);
    }

    private ArticleCommentDto createArticleCommentDto(String content) {
        return createArticleCommentDto(null, content);
    }

    private ArticleCommentDto createArticleCommentDto(Long parentId, String content) {
        return createArticleCommentDto(1L,parentId, content);
    }

    private ArticleCommentDto createArticleCommentDto(Long id, Long parentCommentId, String content) {
        return ArticleCommentDto.of(
                id,
                1L,
                createUserAccountDto(),
                parentCommentId,
                content,
                LocalDateTime.now(),
                "hann",
                LocalDateTime.now(),
                "hann"
        );
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                "uno",
                "password",
                "hanna@email.com",
                "uno",
                null,
                LocalDateTime.now(),
                "uno",
                LocalDateTime.now(),
                "uno"
        );
    }

    private ArticleComment createArticleComment(Long id, String content) {
        ArticleComment articleComment = ArticleComment.of(
                createArticle(),
                createUserAccount(),
                content
        );

        ReflectionTestUtils.setField(articleComment, "id", id);
        return articleComment;
    }

    private UserAccount createUserAccount() {
        return UserAccount.of(
                "uno",
                "password",
                "hanna@email.com",
                "Uno",
                null
        );
    }

    private Article createArticle() {
        Article article = Article.of(
                createUserAccount(),
                "title",
                "content"
        );
        ReflectionTestUtils.setField(article, "id", 1L);
        article.addHashtags(Set.of(createHashtag(article)));

        return article;
    }

    private Hashtag createHashtag(Article article) {
        return Hashtag.of("java");
    }
}

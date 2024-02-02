package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.type.SearchType;
import com.fastcampus.projectboard.dto.ArticleDto;
import com.fastcampus.projectboard.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor    // 필수 필드에 대한 생성자를 자동으로 만들어줌
@Transactional
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType title, String searchKeyword) {
        return Page.empty();
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public ArticleDto searchArticle(long l) {
        return null;
    }
}
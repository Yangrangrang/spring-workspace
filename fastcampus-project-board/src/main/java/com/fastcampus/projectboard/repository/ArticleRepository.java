package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.QArticle;
import com.querydsl.core.types.dsl.DateExpression;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>, // 엔티티 안에 있는 모든 필드에 대한 기본 검색 기능을 추가
        QuerydslBinderCustomizer<QArticle>  // 추가적으로 우리가 원하는 검색 기능을 만들기 위해 추가
{
    @Override
    default void customize(QuerydslBindings bindings, QArticle root){   // 검색에 대한 세부적인 규칙을 다시 재구성 (interface라 구현이 불가능하지만 java8이후로 가능)
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);
//        bindings.bind(root.title).first((path, value) -> path.eq(value)); 밑에가 람다 표현식으로 바꿨을 때 , 처음엔 SimpleExperession으로 되어있지만 변경해줌
//        bindings.bind(root.title).first(StringExpression::likeIgnoreCase);  // 쿼리문이 like '${v}' 이거를 쓸 경우 %를 따로 넣어줘야 한다.
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);  // 쿼리문 like '%${v}%'
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);  // 문자열x DateTimeExpression 로 변경
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}

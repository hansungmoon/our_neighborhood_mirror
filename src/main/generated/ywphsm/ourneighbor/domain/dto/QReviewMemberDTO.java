package ywphsm.ourneighbor.domain.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

import com.querydsl.core.types.Expression;

/**
 * ywphsm.ourneighbor.domain.dto.QReviewMemberDTO is a Querydsl Projection type for ReviewMemberDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QReviewMemberDTO extends ConstructorExpression<ReviewMemberDTO> {

    private static final long serialVersionUID = -469505790L;

    public QReviewMemberDTO(Expression<Long> reviewId, Expression<String> content, Expression<Integer> rating, Expression<java.time.LocalDateTime> createDate, Expression<Long> memberId, Expression<String> username, Expression<String> storedFileName) {
        super(ReviewMemberDTO.class, new Class<?>[]{long.class, String.class, int.class, java.time.LocalDateTime.class, long.class, String.class, String.class}, reviewId, content, rating, createDate, memberId, username, storedFileName);
    }

    public QReviewMemberDTO(NumberExpression<Long> reviewId, StringExpression content, NumberExpression<Integer> rating, TemporalExpression<java.time.LocalDateTime> createDate, StringExpression storedFileName, StringExpression storeName, NumberExpression<Long> storeId) {
        super(ReviewMemberDTO.class, new Class<?>[]{long.class, String.class, int.class, java.time.LocalDateTime.class, String.class, String.class, long.class}, reviewId, content, rating, createDate, storedFileName, storeName, storeId);
    }

}


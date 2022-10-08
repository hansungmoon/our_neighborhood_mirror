package ywphsm.ourneighbor.domain.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * ywphsm.ourneighbor.domain.dto.QReviewMemberDTO is a Querydsl Projection type for ReviewMemberDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QReviewMemberDTO extends ConstructorExpression<ReviewMemberDTO> {

    private static final long serialVersionUID = -469505790L;

    public QReviewMemberDTO(com.querydsl.core.types.Expression<Long> reviewId, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<Integer> rating, com.querydsl.core.types.Expression<java.time.LocalDateTime> createDate, com.querydsl.core.types.Expression<Long> memberId, com.querydsl.core.types.Expression<String> username, com.querydsl.core.types.Expression<String> storedFileName) {
        super(ReviewMemberDTO.class, new Class<?>[]{long.class, String.class, int.class, java.time.LocalDateTime.class, long.class, String.class, String.class}, reviewId, content, rating, createDate, memberId, username, storedFileName);
    }

}


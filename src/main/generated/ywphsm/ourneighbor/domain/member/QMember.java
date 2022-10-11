package ywphsm.ourneighbor.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1153692242L;

    public static final QMember member = new QMember("member1");

    public final ywphsm.ourneighbor.domain.QBaseTimeEntity _super = new ywphsm.ourneighbor.domain.QBaseTimeEntity(this);

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final StringPath birthDate = createString("birthDate");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath email = createString("email");

    public final BooleanPath emailConfirm = createBoolean("emailConfirm");

    public final NumberPath<Integer> gender = createNumber("gender", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final ListPath<MemberOfStore, QMemberOfStore> memberOfStoreList = this.<MemberOfStore, QMemberOfStore>createList("memberOfStoreList", MemberOfStore.class, QMemberOfStore.class, PathInits.DIRECT2);

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final ListPath<ywphsm.ourneighbor.domain.Review, ywphsm.ourneighbor.domain.QReview> reviewList = this.<ywphsm.ourneighbor.domain.Review, ywphsm.ourneighbor.domain.QReview>createList("reviewList", ywphsm.ourneighbor.domain.Review.class, ywphsm.ourneighbor.domain.QReview.class, PathInits.DIRECT2);

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final StringPath userId = createString("userId");

    public final StringPath username = createString("username");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}


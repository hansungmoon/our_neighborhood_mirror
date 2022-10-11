package ywphsm.ourneighbor.domain.store;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStore is a Querydsl query type for Store
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStore extends EntityPathBase<Store> {

    private static final long serialVersionUID = -1313717192L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStore store = new QStore("store");

    public final ywphsm.ourneighbor.domain.QBaseEntity _super = new ywphsm.ourneighbor.domain.QBaseEntity(this);

    public final ywphsm.ourneighbor.domain.embedded.QAddress address;

    public final ywphsm.ourneighbor.domain.embedded.QBusinessTime businessTime;

    public final ListPath<ywphsm.ourneighbor.domain.category.CategoryOfStore, ywphsm.ourneighbor.domain.category.QCategoryOfStore> categoryOfStoreList = this.<ywphsm.ourneighbor.domain.category.CategoryOfStore, ywphsm.ourneighbor.domain.category.QCategoryOfStore>createList("categoryOfStoreList", ywphsm.ourneighbor.domain.category.CategoryOfStore.class, ywphsm.ourneighbor.domain.category.QCategoryOfStore.class, PathInits.DIRECT2);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final ListPath<ywphsm.ourneighbor.domain.hashtag.HashtagOfStore, ywphsm.ourneighbor.domain.hashtag.QHashtagOfStore> hashtagOfStoreList = this.<ywphsm.ourneighbor.domain.hashtag.HashtagOfStore, ywphsm.ourneighbor.domain.hashtag.QHashtagOfStore>createList("hashtagOfStoreList", ywphsm.ourneighbor.domain.hashtag.HashtagOfStore.class, ywphsm.ourneighbor.domain.hashtag.QHashtagOfStore.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath intro = createString("intro");

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final NumberPath<Double> lat = createNumber("lat", Double.class);

    public final NumberPath<Double> lon = createNumber("lon", Double.class);

    public final ListPath<ywphsm.ourneighbor.domain.member.MemberOfStore, ywphsm.ourneighbor.domain.member.QMemberOfStore> memberOfStoreList = this.<ywphsm.ourneighbor.domain.member.MemberOfStore, ywphsm.ourneighbor.domain.member.QMemberOfStore>createList("memberOfStoreList", ywphsm.ourneighbor.domain.member.MemberOfStore.class, ywphsm.ourneighbor.domain.member.QMemberOfStore.class, PathInits.DIRECT2);

    public final ListPath<ywphsm.ourneighbor.domain.menu.Menu, ywphsm.ourneighbor.domain.menu.QMenu> menuList = this.<ywphsm.ourneighbor.domain.menu.Menu, ywphsm.ourneighbor.domain.menu.QMenu>createList("menuList", ywphsm.ourneighbor.domain.menu.Menu.class, ywphsm.ourneighbor.domain.menu.QMenu.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath notice = createString("notice");

    public final ListPath<String, StringPath> offDays = this.<String, StringPath>createList("offDays", String.class, StringPath.class, PathInits.DIRECT2);

    public final StringPath phoneNumber = createString("phoneNumber");

    public final NumberPath<Integer> ratingTotal = createNumber("ratingTotal", Integer.class);

    public final ListPath<ywphsm.ourneighbor.domain.Review, ywphsm.ourneighbor.domain.QReview> reviewList = this.<ywphsm.ourneighbor.domain.Review, ywphsm.ourneighbor.domain.QReview>createList("reviewList", ywphsm.ourneighbor.domain.Review.class, ywphsm.ourneighbor.domain.QReview.class, PathInits.DIRECT2);

    public final EnumPath<StoreStatus> status = createEnum("status", StoreStatus.class);

    public QStore(String variable) {
        this(Store.class, forVariable(variable), INITS);
    }

    public QStore(Path<? extends Store> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStore(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStore(PathMetadata metadata, PathInits inits) {
        this(Store.class, metadata, inits);
    }

    public QStore(Class<? extends Store> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new ywphsm.ourneighbor.domain.embedded.QAddress(forProperty("address")) : null;
        this.businessTime = inits.isInitialized("businessTime") ? new ywphsm.ourneighbor.domain.embedded.QBusinessTime(forProperty("businessTime")) : null;
    }

}


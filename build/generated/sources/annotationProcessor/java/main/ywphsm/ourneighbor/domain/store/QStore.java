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

    public final ywphsm.ourneighbor.domain.QBaseTimeEntity _super = new ywphsm.ourneighbor.domain.QBaseTimeEntity(this);

    public final ywphsm.ourneighbor.domain.QAddress address;

    public final DateTimePath<java.time.LocalDateTime> breakEnd = createDateTime("breakEnd", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> breakStart = createDateTime("breakStart", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> closingTime = createDateTime("closingTime", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath intro = createString("intro");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final NumberPath<Long> latitude = createNumber("latitude", Long.class);

    public final NumberPath<Long> longitude = createNumber("longitude", Long.class);

    public final ywphsm.ourneighbor.domain.member.QMember member;

    public final ListPath<ywphsm.ourneighbor.domain.Menu, ywphsm.ourneighbor.domain.QMenu> menuList = this.<ywphsm.ourneighbor.domain.Menu, ywphsm.ourneighbor.domain.QMenu>createList("menuList", ywphsm.ourneighbor.domain.Menu.class, ywphsm.ourneighbor.domain.QMenu.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath notice = createString("notice");

    public final NumberPath<Integer> offDay = createNumber("offDay", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> openingTime = createDateTime("openingTime", java.time.LocalDateTime.class);

    public final StringPath phoneNumber = createString("phoneNumber");

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
        this.address = inits.isInitialized("address") ? new ywphsm.ourneighbor.domain.QAddress(forProperty("address")) : null;
        this.member = inits.isInitialized("member") ? new ywphsm.ourneighbor.domain.member.QMember(forProperty("member")) : null;
    }

}


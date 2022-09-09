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

    public final TimePath<java.time.LocalTime> breakEnd = createTime("breakEnd", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> breakStart = createTime("breakStart", java.time.LocalTime.class);

    public final ListPath<ywphsm.ourneighbor.domain.CategoryOfStore, ywphsm.ourneighbor.domain.QCategoryOfStore> categoryOfStoreList = this.<ywphsm.ourneighbor.domain.CategoryOfStore, ywphsm.ourneighbor.domain.QCategoryOfStore>createList("categoryOfStoreList", ywphsm.ourneighbor.domain.CategoryOfStore.class, ywphsm.ourneighbor.domain.QCategoryOfStore.class, PathInits.DIRECT2);

    public final TimePath<java.time.LocalTime> closingTime = createTime("closingTime", java.time.LocalTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath intro = createString("intro");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final NumberPath<Double> lat = createNumber("lat", Double.class);

    public final NumberPath<Double> lon = createNumber("lon", Double.class);

    public final ListPath<ywphsm.ourneighbor.domain.Menu, ywphsm.ourneighbor.domain.QMenu> menuList = this.<ywphsm.ourneighbor.domain.Menu, ywphsm.ourneighbor.domain.QMenu>createList("menuList", ywphsm.ourneighbor.domain.Menu.class, ywphsm.ourneighbor.domain.QMenu.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath notice = createString("notice");

    public final ListPath<String, StringPath> offDays = this.<String, StringPath>createList("offDays", String.class, StringPath.class, PathInits.DIRECT2);

    public final TimePath<java.time.LocalTime> openingTime = createTime("openingTime", java.time.LocalTime.class);

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
    }

}


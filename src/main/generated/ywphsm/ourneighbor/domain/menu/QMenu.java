package ywphsm.ourneighbor.domain.menu;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMenu is a Querydsl query type for Menu
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMenu extends EntityPathBase<Menu> {

    private static final long serialVersionUID = -2796430L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMenu menu = new QMenu("menu");

    public final ywphsm.ourneighbor.domain.QBaseEntity _super = new ywphsm.ourneighbor.domain.QBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final DateTimePath<java.time.LocalDateTime> discountEnd = createDateTime("discountEnd", java.time.LocalDateTime.class);

    public final NumberPath<Integer> discountPrice = createNumber("discountPrice", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> discountStart = createDateTime("discountStart", java.time.LocalDateTime.class);

    public final ywphsm.ourneighbor.domain.file.QUploadFile file;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final ywphsm.ourneighbor.domain.store.QStore store;

    public final EnumPath<MenuType> type = createEnum("type", MenuType.class);

    public QMenu(String variable) {
        this(Menu.class, forVariable(variable), INITS);
    }

    public QMenu(Path<? extends Menu> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMenu(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMenu(PathMetadata metadata, PathInits inits) {
        this(Menu.class, metadata, inits);
    }

    public QMenu(Class<? extends Menu> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.file = inits.isInitialized("file") ? new ywphsm.ourneighbor.domain.file.QUploadFile(forProperty("file"), inits.get("file")) : null;
        this.store = inits.isInitialized("store") ? new ywphsm.ourneighbor.domain.store.QStore(forProperty("store"), inits.get("store")) : null;
    }

}


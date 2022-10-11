package ywphsm.ourneighbor.domain.category;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCategoryOfStore is a Querydsl query type for CategoryOfStore
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategoryOfStore extends EntityPathBase<CategoryOfStore> {

    private static final long serialVersionUID = 1585831192L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCategoryOfStore categoryOfStore = new QCategoryOfStore("categoryOfStore");

    public final QCategory category;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ywphsm.ourneighbor.domain.store.QStore store;

    public QCategoryOfStore(String variable) {
        this(CategoryOfStore.class, forVariable(variable), INITS);
    }

    public QCategoryOfStore(Path<? extends CategoryOfStore> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCategoryOfStore(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCategoryOfStore(PathMetadata metadata, PathInits inits) {
        this(CategoryOfStore.class, metadata, inits);
    }

    public QCategoryOfStore(Class<? extends CategoryOfStore> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category"), inits.get("category")) : null;
        this.store = inits.isInitialized("store") ? new ywphsm.ourneighbor.domain.store.QStore(forProperty("store"), inits.get("store")) : null;
    }

}


package ywphsm.ourneighbor.domain.hashtag;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHashtagOfStore is a Querydsl query type for HashtagOfStore
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHashtagOfStore extends EntityPathBase<HashtagOfStore> {

    private static final long serialVersionUID = 1715140892L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHashtagOfStore hashtagOfStore = new QHashtagOfStore("hashtagOfStore");

    public final QHashtag hashtag;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ywphsm.ourneighbor.domain.store.QStore store;

    public QHashtagOfStore(String variable) {
        this(HashtagOfStore.class, forVariable(variable), INITS);
    }

    public QHashtagOfStore(Path<? extends HashtagOfStore> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHashtagOfStore(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHashtagOfStore(PathMetadata metadata, PathInits inits) {
        this(HashtagOfStore.class, metadata, inits);
    }

    public QHashtagOfStore(Class<? extends HashtagOfStore> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hashtag = inits.isInitialized("hashtag") ? new QHashtag(forProperty("hashtag")) : null;
        this.store = inits.isInitialized("store") ? new ywphsm.ourneighbor.domain.store.QStore(forProperty("store"), inits.get("store")) : null;
    }

}


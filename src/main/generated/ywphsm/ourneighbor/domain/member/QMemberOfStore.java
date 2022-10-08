package ywphsm.ourneighbor.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberOfStore is a Querydsl query type for MemberOfStore
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberOfStore extends EntityPathBase<MemberOfStore> {

    private static final long serialVersionUID = -1840591208L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberOfStore memberOfStore = new QMemberOfStore("memberOfStore");

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public final QMember member;

    public final ywphsm.ourneighbor.domain.store.QStore store;

    public QMemberOfStore(String variable) {
        this(MemberOfStore.class, forVariable(variable), INITS);
    }

    public QMemberOfStore(Path<? extends MemberOfStore> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberOfStore(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberOfStore(PathMetadata metadata, PathInits inits) {
        this(MemberOfStore.class, metadata, inits);
    }

    public QMemberOfStore(Class<? extends MemberOfStore> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.store = inits.isInitialized("store") ? new ywphsm.ourneighbor.domain.store.QStore(forProperty("store"), inits.get("store")) : null;
    }

}


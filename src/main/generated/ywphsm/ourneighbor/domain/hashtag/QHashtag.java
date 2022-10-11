package ywphsm.ourneighbor.domain.hashtag;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHashtag is a Querydsl query type for Hashtag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHashtag extends EntityPathBase<Hashtag> {

    private static final long serialVersionUID = 2112672846L;

    public static final QHashtag hashtag = new QHashtag("hashtag");

    public final ListPath<HashtagOfStore, QHashtagOfStore> hashtagOfStoreList = this.<HashtagOfStore, QHashtagOfStore>createList("hashtagOfStoreList", HashtagOfStore.class, QHashtagOfStore.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QHashtag(String variable) {
        super(Hashtag.class, forVariable(variable));
    }

    public QHashtag(Path<? extends Hashtag> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHashtag(PathMetadata metadata) {
        super(Hashtag.class, metadata);
    }

}


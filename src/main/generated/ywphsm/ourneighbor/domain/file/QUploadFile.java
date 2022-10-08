package ywphsm.ourneighbor.domain.file;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUploadFile is a Querydsl query type for UploadFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUploadFile extends EntityPathBase<UploadFile> {

    private static final long serialVersionUID = 2141245363L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUploadFile uploadFile = new QUploadFile("uploadFile");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ywphsm.ourneighbor.domain.menu.QMenu menu;

    public final ywphsm.ourneighbor.domain.QReview review;

    public final StringPath storedFileName = createString("storedFileName");

    public final StringPath uploadedFileName = createString("uploadedFileName");

    public QUploadFile(String variable) {
        this(UploadFile.class, forVariable(variable), INITS);
    }

    public QUploadFile(Path<? extends UploadFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUploadFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUploadFile(PathMetadata metadata, PathInits inits) {
        this(UploadFile.class, metadata, inits);
    }

    public QUploadFile(Class<? extends UploadFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.menu = inits.isInitialized("menu") ? new ywphsm.ourneighbor.domain.menu.QMenu(forProperty("menu"), inits.get("menu")) : null;
        this.review = inits.isInitialized("review") ? new ywphsm.ourneighbor.domain.QReview(forProperty("review"), inits.get("review")) : null;
    }

}


package ywphsm.ourneighbor.domain.embedded;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBusinessTime is a Querydsl query type for BusinessTime
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBusinessTime extends BeanPath<BusinessTime> {

    private static final long serialVersionUID = 949852533L;

    public static final QBusinessTime businessTime = new QBusinessTime("businessTime");

    public final TimePath<java.time.LocalTime> breakEnd = createTime("breakEnd", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> breakStart = createTime("breakStart", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> closingTime = createTime("closingTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> openingTime = createTime("openingTime", java.time.LocalTime.class);

    public QBusinessTime(String variable) {
        super(BusinessTime.class, forVariable(variable));
    }

    public QBusinessTime(Path<? extends BusinessTime> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBusinessTime(PathMetadata metadata) {
        super(BusinessTime.class, metadata);
    }

}


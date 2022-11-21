package ywphsm.ourneighbor.repository.requestAddStore;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RequestAddStoreRepositoryImpl implements RequestAddStoreRepositoryCustom{

    private final JPAQueryFactory queryFactory;
}

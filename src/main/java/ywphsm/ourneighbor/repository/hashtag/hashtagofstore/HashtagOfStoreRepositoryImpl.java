package ywphsm.ourneighbor.repository.hashtag.hashtagofstore;


import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ywphsm.ourneighbor.domain.dto.hashtag.HashtagOfStoreDTO;

import java.util.List;

import static ywphsm.ourneighbor.domain.hashtag.QHashtagOfStore.*;

@Slf4j
@RequiredArgsConstructor
public class HashtagOfStoreRepositoryImpl implements HashtagOfStoreRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<HashtagOfStoreDTO.WithCount> findHashtagAndCountByStoreIdOrderByCountDescTop9(Long storeId) {
        return queryFactory
                .select(
                        Projections.constructor(HashtagOfStoreDTO.WithCount.class,
                                hashtagOfStore.hashtag.id, hashtagOfStore.hashtag.name, hashtagOfStore.store.id,
                                hashtagOfStore.id.count().as("count"))
                )
                .from(hashtagOfStore)
                .groupBy(hashtagOfStore.hashtag, hashtagOfStore.store)
                .having(hashtagOfStore.store.id.eq(storeId))
                .orderBy(hashtagOfStore.id.count().desc())
                .limit(9)
                .fetch();
    }

    @Override
    public List<HashtagOfStoreDTO.WithCount> findHashtagAndCountByStoreIdOrderByCountDescOrderByHashtagName(Long storeId) {
        return queryFactory
                .select(
                        Projections.constructor(HashtagOfStoreDTO.WithCount.class,
                                hashtagOfStore.hashtag.id, hashtagOfStore.hashtag.name, hashtagOfStore.store.id,
                                hashtagOfStore.id.count().as("count"))
                )
                .from(hashtagOfStore)
                .groupBy(hashtagOfStore.hashtag, hashtagOfStore.store)
                .having(hashtagOfStore.store.id.eq(storeId))
                .orderBy(hashtagOfStore.id.count().desc(), hashtagOfStore.hashtag.name.asc())
                .fetch();
    }

    @Override
    public Long deleteByHashtagIdByStoreId(Long hashtagId, Long storeId) {
        return queryFactory
                .delete(hashtagOfStore)
                .where(hashtagOfStore.hashtag.id.eq(hashtagId).and(hashtagOfStore.store.id.eq(storeId)))
                .execute();
    }

}

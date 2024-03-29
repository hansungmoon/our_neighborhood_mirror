package ywphsm.ourneighbor.repository.menu;

import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ywphsm.ourneighbor.domain.menu.Menu;
import ywphsm.ourneighbor.domain.menu.MenuType;

import java.util.List;

import static ywphsm.ourneighbor.domain.file.QUploadFile.*;
import static ywphsm.ourneighbor.domain.menu.QMenu.menu;

@Slf4j
@RequiredArgsConstructor
public class MenuRepositoryImpl implements MenuRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Menu> findByStoreIdWithoutMenuTypeIsMenuCaseByOrderByType(Long storeId) {
        NumberExpression<Integer> typeRank = new CaseBuilder()
                .when(menu.type.eq(MenuType.MAIN)).then(1)
                .when(menu.type.eq(MenuType.SIDE)).then(2)
                .when(menu.type.eq(MenuType.DESSERT)).then(3)
                .when(menu.type.eq(MenuType.BEVERAGE)).then(4)
                .when(menu.type.eq(MenuType.DRINK)).then(5)
                .otherwise(6);

        return queryFactory
                .selectFrom(menu)
                .where(menu.store.id.eq(storeId).and(menu.type.ne(MenuType.MENU)))
                .innerJoin(menu.file, uploadFile)
                .fetchJoin()
                .orderBy(typeRank.asc())
                .fetch();
    }

    @Override
    public List<String> findImageByMenuTypeIsMenu(Long storeId) {
        return queryFactory
                .select(menu.file.uploadImageUrl)
                .from(menu)
                .where(menu.type.eq(MenuType.MENU).and(menu.store.id.eq(storeId)))
                .fetch();
    }
}

package ywphsm.ourneighbor.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ywphsm.ourneighbor.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {

    @Query("select c from Category c where c.parent is NULL")
    List<Category> findCategories();

    Optional<Category> findByNameAndDepth(String name, Long depth);

    // 카테고리 존재 여부를 확인
    Boolean existsByNameAndDepth(String name, Long depth);
}

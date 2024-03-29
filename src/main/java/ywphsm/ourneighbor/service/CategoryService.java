package ywphsm.ourneighbor.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ywphsm.ourneighbor.domain.category.Category;
import ywphsm.ourneighbor.domain.dto.category.CategoryDTO;
import ywphsm.ourneighbor.repository.category.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // 카테고리 등록
    @Transactional
    public Long save(CategoryDTO.Detail dto) {
        // parent, child는 빠져있음
        Category category = dto.toEntity();

        // 가장 상위 (depth : 1L) 카테고리 생성
        if (dto.getParentId() == null) {
            // 모든 카테고리를 다 볼 수 있는 루트 카테고리
            // 없으면 생성함
            Category root = categoryRepository.findByNameAndDepth("ROOT", 0L)
                    .orElseGet(() -> Category.builder()
                            .name("ROOT")
                            .depth(0L)
                            .build()
                    );

            // 이전에 만들어진 적이 없는 경우에만 루트 카테고리를 저장
            if (!categoryRepository.existsByNameAndDepth("ROOT", 0L)) {
                categoryRepository.save(root);
            }

            category.addParentCategoryAndDepth(root, 1L);
        } else {
            Category parent = categoryRepository.findById(dto.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상위 카테고리입니다."));

            category.addParentCategoryAndDepth(parent, parent.getDepth() + 1);
            parent.getChildren().add(category);
        }

        return categoryRepository.save(category).getId();
    }

    @Transactional
    public Long delete(Long categoryId) {
        // findById + delete 조합 => 에러 발생 시 개발자가 직접 커스텀 가능
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new IllegalArgumentException("해당 카테고리가 없습니다. categoryId = " + categoryId));
        categoryRepository.delete(category);

        return categoryId;
    }

    @Transactional
    public void deleteByCategoryLinkedCategoryOfStore(Category category) {
        categoryRepository.deleteByCategoryLinkedCategoryOfStore(category);
    }

    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다. id = " + categoryId));
    }

    public List<CategoryDTO.Simple> findByDepthCaseByOrderByName(Long depth) {
        return categoryRepository.findByDepthCaseByOrderByName(depth).stream()
                .map(CategoryDTO.Simple::of).collect(Collectors.toList());
    }

    /*
        모든 카테고리들을 조건에 맞게 정렬하여 보여주는 쿼리
        카테고리 등록 시, 카테고리 리스트 보여줄 때 사용
     */
    public List<CategoryDTO.Detail> findAllByOrderByDepthAscParentIdAscNameAsc() {
        return categoryRepository.findAllByOrderByDepthAscParentIdAscNameAsc().stream()
                .map(CategoryDTO.Detail::new).collect(Collectors.toList());
    }

    /*
        하나의 쿼리로 모든 하위 카테고리를 연쇄적으로 뽑아내기 위한 쿼리
        ParentIsNull인 Category는 ROOT 밖에 없음
        ROOT를 찾은 다음 그의 자식 카테고리를 계속 찾으면 전체 카테고리를 다 찾을 수 있음
     */
    public CategoryDTO.Detail findAllCategoriesHier() {
        Category category = categoryRepository.findByParentIsNull()
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

        return CategoryDTO.Detail.of(category);
    }

    /*
        카테고리 중복 체크 로직
     */
    public boolean checkCategoryDuplicateByNameAndParent(String name, Category parent) {
        return categoryRepository.existsByNameAndParent(name, parent);
    }
}
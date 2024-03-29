package ywphsm.ourneighbor.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ywphsm.ourneighbor.domain.category.Category;
import ywphsm.ourneighbor.domain.dto.category.CategoryDTO;
import ywphsm.ourneighbor.service.CategoryService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CategoryApiController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public ResultClass<?> findAllByOrderByDepthAscParentIdAscNameAsc() {
        List<CategoryDTO.Detail> categories = categoryService.findAllByOrderByDepthAscParentIdAscNameAsc();
        return new ResultClass<>(categories);
    }

    @GetMapping("/categories-hier")
    public CategoryDTO.Detail findAllCategoriesHier() {
        return categoryService.findAllCategoriesHier();      // 대분류
    }

    @GetMapping("/categories-hier-edit")
    public Long findCategoryParentId(Long categoryId) {
        Category category = categoryService.findById(categoryId);
        return category.getParent().getId();
    }

    @GetMapping("/category-check")
    public ResponseEntity<Boolean> checkCategoryDuplicate(String name, Long parentId) {
        Category parent = categoryService.findById(parentId);
        return ResponseEntity.ok(categoryService.checkCategoryDuplicateByNameAndParent(name, parent));
    }


    // @RequestBody 생략 시, Test에서 받아오질 못함
    @PostMapping(value = "/admin/category", produces = "application/json;")
    public Long save(@RequestBody CategoryDTO.Detail dto) {
        return categoryService.save(dto);
    }

    @DeleteMapping("/admin/category/{categoryId}")
    public Long delete(@PathVariable Long categoryId) {
        return categoryService.delete(categoryId);
    }
}
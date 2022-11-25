package ywphsm.ourneighbor.domain.category;

import lombok.*;
import ywphsm.ourneighbor.domain.RecommendPost;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString(of = {"id", "name", "depth"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    private Long depth;

    /*
        카테고리 계층형 구조로 표현
            => 셀프로 양방향 연관 관계를 걸어줌
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;
    
    public void addParentCategoryAndDepth(Category parent, Long depth) {
        this.parent = parent;
        this.depth = depth;
    }

    @OneToMany(mappedBy = "parent")
    private List<Category> children = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    private List<CategoryOfStore> categoryOfStoreList = new ArrayList<>();

    @Builder
    public Category(Long id, String name, Long depth,
                    Category parent, List<Category> children,
                    List<CategoryOfStore> categoryOfStoreList) {
        this.id = id;
        this.name = name;
        this.depth = depth;
        this.parent = parent;
        this.children = children;
        this.categoryOfStoreList = categoryOfStoreList;
    }

    /*
        생성자
     */
    @Builder
    public Category(String name, Long depth, Category parent) {
        this.name = name;
        this.depth = depth;
        this.parent = parent;
    }


    /*
        === 연관 관계 편의 메소드 ===
    */

}
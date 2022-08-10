package ywphsm.ourneighbor.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ywphsm.ourneighbor.domain.store.Store;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {
        "id", "name", "price", "discountPrice",
        "discountStart", "discountEnd"
})
public class Menu {

    @Id
    @GeneratedValue
    @Column(name = "menu_id")
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Integer price;

    private int discountPrice;
    private LocalDateTime discountStart;
    private LocalDateTime discountEnd;


    /*
        JPA 연관 관계 매핑
     */
    /*
        Store (One) <==> Menu (Many)
        mappedBy가 없는 쪽이 연관 관계의 주인
            ==> Menu 엔티티의 Store가 연관 관계의 주인
            ==> FK가 있는 엔티티가 연관 관계의 주인
            ==> ManyToOne인 경우, Many 쪽이 항상 연관 관계의 주인
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    // 데이터 필수 변경이므로 setter를 열었음
    public void setStore(Store store) {
        this.store = store;
    }

    /*
        생성자를 강제하고 setter를 닫음으로써 값이 변경될 가능성을 차단함
        수정이 필요한 경우의 메소드는 별도로 작성하자
    */
    public Menu(String name, Integer price, int discountPrice,
                LocalDateTime discountStart, LocalDateTime discountEnd,
                Store store) {
        this.name = name;
        this.price = price;
        this.discountPrice = discountPrice;
        this.discountStart = discountStart;
        this.discountEnd = discountEnd;
        this.store = store;
    }

    /*
        === 연관 관계 편의 메소드 ===
     */



}

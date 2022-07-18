package ywphsm.ourneighbor.domain.store;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ywphsm.ourneighbor.domain.Address;
import ywphsm.ourneighbor.domain.BaseTimeEntity;
import ywphsm.ourneighbor.domain.CategoryOfStore;
import ywphsm.ourneighbor.domain.Menu;
import ywphsm.ourneighbor.domain.member.Member;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {
        "id", "name", "longitude", "latitude",
        "phoneNumber", "openingTime", "closingTime",
        "breakStart", "breakEnd", "notice", "intro",
        "offDay", "status"
})
public class Store extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "store_id")
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Long longitude;                // 경도

    @NotNull
    private Long latitude;                 // 위도

    @NotBlank
    private String phoneNumber;

    @Column(name = "opening_time")
    @NotNull
    private LocalDateTime openingTime;        // 여는 시간

    @Column(name = "closing_time")
    @NotNull
    private LocalDateTime closingTime;        // 닫는 시간

    @Column(name = "break_start")
    @NotNull
    private LocalDateTime breakStart;         // 쉬는 시간 시작

    @Column(name = "break_end")
    @NotNull
    private LocalDateTime breakEnd;           // 쉬는 시간 끝

    private String notice;                    // 가게 소식
    private String intro;                     // 가게 소개

    private Integer offDay;                   // 쉬는 날 (0 : 일요일 ~ 6 : 토요일)

    @Enumerated(EnumType.STRING)
    private StoreStatus status;               // 가게 오픈 상황

    // 주소는 임베디드 타입으로 받음
    @Embedded
    private Address address;

    /*
        JPA 연관 관계 매핑
     */
    /*
        Store (One) <==> Menu (Many)
        mappedBy가 없는 쪽이 연관 관계의 주인
            ==> Menu 엔티티의 Store가 연관 관계의 주인
            ==> FK가 있는 엔티티가 연관 관계의 주인
            ==> ManyToOne인 경우, Many 쪽이 항상 연관 관계의 주인

        생각해보면 Menu를 보고 Store를 불러올 경우는 없다
        아래는 양방향 관계를 맺을 때 다시 사용
     */
    @OneToMany(mappedBy = "store")
    private List<Menu> menuList = new ArrayList<>();

    // Many To Many인듯
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // Category와 양방향은 보류



    /*
        생성자
     */
    public Store(String name, Long longitude, Long latitude,
                 String phoneNumber, LocalDateTime openingTime, LocalDateTime closingTime,
                 LocalDateTime breakStart, LocalDateTime breakEnd, String notice, String intro,
                 Integer offDay, StoreStatus status, Address address) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.phoneNumber = phoneNumber;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.breakStart = breakStart;
        this.breakEnd = breakEnd;
        this.notice = notice;
        this.intro = intro;
        this.offDay = offDay;
        this.status = status;
        this.address = address;
    }

    /*
        === 연관 관계 편의 메소드 ===
     */
    public void addMenu(Menu menu) {
        menu.setStore(this);
        menuList.add(menu);
    }





    /*
        === 생성 메소드 ===
    */


    /*
        비즈니스 로직 추가
     */






}

package ywphsm.ourneighbor.repository.store.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import ywphsm.ourneighbor.domain.Address;
import ywphsm.ourneighbor.domain.store.Store;
import ywphsm.ourneighbor.domain.store.StoreStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
public class StoreDetailDTO {

    private Long storeId;

    @NotBlank
    private String name;

    private String phoneNumber;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime openingTime;            // 여는 시간

    @NotNull
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime closingTime;            // 닫는 시간

    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime breakStart;             // 쉬는 시간 시작

    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime breakEnd;               // 쉬는 시간 끝

    private String notice;                    // 가게 소식
    private String intro;                     // 가게 소개

    private Integer offDay;                   // 쉬는 날 (0 : 일요일 ~ 6 : 토요일)

    private StoreStatus status;               // 가게 오픈 상황

    // 주소는 임베디드 타입으로 받음
    private Address address;

    // 나중에
//    private List<Menu> menuList;


    @Builder
    public StoreDetailDTO(Store store) {
        storeId = store.getId();
        name = store.getName();
        phoneNumber = store.getPhoneNumber();
        openingTime = store.getOpeningTime();
        closingTime = store.getClosingTime();
        breakStart = store.getBreakStart();
        breakEnd = store.getBreakEnd();
        notice = store.getNotice();
        intro = store.getIntro();
        offDay = store.getOffDay();
        status = store.getStatus();
        address = store.getAddress();
    }

    public Store toEntity() {
        return Store.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .openingTime(openingTime)
                .closingTime(closingTime)
                .breakStart(breakStart)
                .breakEnd(breakEnd)
                .notice(notice)
                .intro(intro)
                .offDay(offDay)
                .status(status)
                .address(address)
                .build();
    }
}

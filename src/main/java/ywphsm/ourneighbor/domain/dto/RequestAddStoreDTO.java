package ywphsm.ourneighbor.domain.dto;

import lombok.Data;
import ywphsm.ourneighbor.domain.embedded.Address;
import ywphsm.ourneighbor.domain.member.Member;
import ywphsm.ourneighbor.domain.store.RequestAddStore;

import javax.validation.constraints.NotBlank;

@Data
public class RequestAddStoreDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String zipcode;

    @NotBlank
    private String roadAddr;

    @NotBlank
    private String numberAddr;

    private Member member;

    public RequestAddStore toEntity() {
        return RequestAddStore.builder()
                .name(name)
                .address(new Address(roadAddr, numberAddr, zipcode, null))
                .member(member)
                .build();
    }

}

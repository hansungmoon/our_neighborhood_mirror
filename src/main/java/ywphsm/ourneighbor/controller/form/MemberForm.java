package ywphsm.ourneighbor.controller.form;

import lombok.Data;
import ywphsm.ourneighbor.entity.Gender;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MemberForm {

    @NotBlank
    private String username;

    @NotNull
    private Integer age;

    @NotNull
    private Long phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String name;
}

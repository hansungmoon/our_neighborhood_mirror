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

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

    @NotBlank
    private String passwordCheck;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String certifiedNumber;
}

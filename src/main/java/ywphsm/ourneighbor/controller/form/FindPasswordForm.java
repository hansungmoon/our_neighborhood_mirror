package ywphsm.ourneighbor.controller.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FindPasswordForm {

    @NotBlank
    private String userId;

    @NotBlank
    private String newPassword;

    @NotBlank
    private String passwordCheck;
}

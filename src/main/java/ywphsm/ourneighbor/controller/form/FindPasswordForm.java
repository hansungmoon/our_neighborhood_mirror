package ywphsm.ourneighbor.controller.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class
FindPasswordForm {

    @NotBlank
    private String userId;

    @Email
    private String email;
}

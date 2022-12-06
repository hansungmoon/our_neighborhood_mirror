package ywphsm.ourneighbor.controller.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class EditForm {

    @NotBlank
    private String nickname;

    @Email
    @NotBlank
    private String email;

    private String phoneNumber;

    private String imgUrl;

}

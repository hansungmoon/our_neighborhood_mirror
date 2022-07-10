package ywphsm.ourneighbor.controller.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PhoneNumForm {

    @NotBlank
    private String phoneNumber;
}

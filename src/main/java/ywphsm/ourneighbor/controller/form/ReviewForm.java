package ywphsm.ourneighbor.controller.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReviewForm {

    @NotBlank
    private String title;

    @NotBlank   //글자수 제한해야함
    private String content;

}

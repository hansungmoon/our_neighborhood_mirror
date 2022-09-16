package ywphsm.ourneighbor.controller.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class ReviewForm {

    @NotBlank
    private String title;

    @NotBlank   //글자수 제한해야함
    @Pattern(regexp = ".{200}",
            message = "200자 이하여야 합니다")
    private String content;

}

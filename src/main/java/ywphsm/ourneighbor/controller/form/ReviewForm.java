package ywphsm.ourneighbor.controller.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ReviewForm {

    @NotBlank
    @Size(max = 200)
    private String content;

}

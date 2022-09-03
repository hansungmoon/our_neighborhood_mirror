package ywphsm.ourneighbor.controller.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
<<<<<<< HEAD
=======
import javax.validation.constraints.Pattern;
>>>>>>> 118caa3cd9b991f0dd390a415068ce56a3133f97

@Data
public class FindPasswordForm {

    @NotBlank
    private String userId;

    @NotBlank
<<<<<<< HEAD
=======
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*\\W)(?=\\S+$).{6,12}",
            message = "비밀번호는 영문자와 숫자, 특수기호가 적어도 1개 이상 포함된 6자~12자의 비밀번호여야 합니다.")
>>>>>>> 118caa3cd9b991f0dd390a415068ce56a3133f97
    private String newPassword;

    @NotBlank
    private String passwordCheck;
}

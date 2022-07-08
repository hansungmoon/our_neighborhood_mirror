package ywphsm.ourneighbor.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotBlank
    private String username;

    @NotNull
    private Integer age;

    @NotNull
    private Long phoneNumber;

    @NotNull
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

    public Member(String username, Integer age, Long phoneNumber, Gender gender, String loginId, String password, String email, String name) {
        this.username = username;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.name = name;
    }
}

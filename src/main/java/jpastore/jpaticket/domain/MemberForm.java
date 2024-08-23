package jpastore.jpaticket.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {

    @Size(min=3, max=20)
    @NotEmpty(message = "사용자 ID를 입력해 주세요.")
    private String userName;

    @NotEmpty(message = "비밀번호를 입력해 주세요.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    @NotEmpty(message = "비밀번호를 확인해 주세요.")
    private String password2;

    @NotEmpty(message = "이메일을 입력해 주세요.")
    @Email
    private String email;
}

package com.maddog.articket.passwordchangeform.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 密碼更新表單 DTO
 */
@Getter
@Setter
public class PasswordChangeForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "當前密碼不能為空")
    private String currentPassword;

    @NotEmpty(message = "新密碼不能為空")
    @Size(min = 6, message = "新密碼長度至少為6位")
    private String newPassword;

	@NotEmpty(message = "確認密碼不能為空")
    private String confirmPassword;

}

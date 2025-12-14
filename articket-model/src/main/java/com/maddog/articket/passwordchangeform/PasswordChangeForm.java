package com.maddog.articket.passwordchangeform;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PasswordChangeForm {

	
    @NotEmpty(message = "當前密碼不能為空")
    private String currentPassword;

    @NotEmpty(message = "新密碼不能為空")
    @Size(min = 6, message = "新密碼長度至少為6位")
    private String newPassword;

    public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@NotEmpty(message = "確認密碼不能為空")
    private String confirmPassword;
}

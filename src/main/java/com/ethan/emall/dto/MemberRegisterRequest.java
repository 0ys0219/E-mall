package com.ethan.emall.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class MemberRegisterRequest implements Serializable{

    private static final long serialVersionUID = 1L;

	@NotBlank
    private String account;

    @NotBlank
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

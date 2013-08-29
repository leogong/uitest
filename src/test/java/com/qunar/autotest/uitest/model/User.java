package com.qunar.autotest.uitest.model;

import java.io.Serializable;

public class User implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -5241297776923349698L;
    private String username;
    private String password;
    private String email;
    private String type;
    private Boolean isLeaveURL;
    private Integer awayFromLeaveURL;
    private Boolean isSign;
    private Integer awayFromSign;
    private Integer totalMessage;

    public Integer getTotalMessage() {
        return totalMessage;
    }

    public void setTotalMessage(Integer totalMessage) {
        this.totalMessage = totalMessage;
    }

    public User(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getLeaveURL() {
        return isLeaveURL;
    }

    public void setLeaveURL(Boolean leaveURL) {
        isLeaveURL = leaveURL;
    }

    public Integer getAwayFromLeaveURL() {
        return awayFromLeaveURL;
    }

    public void setAwayFromLeaveURL(Integer awayFromLeaveURL) {
        this.awayFromLeaveURL = awayFromLeaveURL;
    }

    public Boolean getSign() {
        return isSign;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSign(Boolean sign) {
        isSign = sign;
    }

    public Integer getAwayFromSign() {
        return awayFromSign;
    }


    public void setAwayFromSign(Integer awayFromSign) {
        this.awayFromSign = awayFromSign;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return username + "," + password + "," + email;
    }
}

package com.avmakarov.school.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "log_user")
public class User extends AbstractBaseEntity {

    @NotBlank
    @Size(max = 100)
    @Column(name = "login", nullable = false)
    private String login;

    @NotBlank
    @Size(max = 1000)
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank
    @Size(max = 1000)
    @Column(name = "info", nullable = false)
    private String info;

    public User() {
    }

    public User(String login, String password, String info) {
        this.login = login;
        this.password = password;
        this.info = info;
    }

    // геттеры и сеттеры
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}

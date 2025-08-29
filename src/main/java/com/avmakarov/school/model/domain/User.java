package com.avmakarov.school.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.type.NumericBooleanConverter;

import java.util.List;

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

    @Column(name = "is_admin")
    @Convert(converter = NumericBooleanConverter.class)
    private boolean admin;

    @Column(name = "is_active")
    @Convert(converter = NumericBooleanConverter.class)
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "log_user_students",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
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

package com.avmakarov.school.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "subject")
public class Subject extends AbstractBaseEntity {

    @NotBlank
    @Size(max = 1000)
    @Column(name = "title", nullable = false)
    private String title;

    @NotBlank
    @Size(max = 1000)
    @Column(name = "comment", nullable = false)
    private String comment;

    public Subject() {
    }

    public Subject(String title, String comment) {
        this.title = title;
        this.comment = comment;
    }

    // геттеры и сеттеры
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}

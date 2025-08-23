package com.avmakarov.school.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "attachment")
public class Attachment extends AbstractBaseEntity {

    @NotBlank
    @Size(max = 100)
    @Column(name = "title", nullable = false)
    private String title;

    @NotBlank
    @Size(max = 4000)
    @Column(name = "href", nullable = false)
    private String href;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    public Attachment() {
    }

    public Attachment(String title, String href, Exercise exercise) {
        this.title = title;
        this.href = href;
        this.exercise = exercise;
    }

    // геттеры и сеттеры
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}

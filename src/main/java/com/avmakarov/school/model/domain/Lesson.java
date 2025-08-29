package com.avmakarov.school.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "log_lesson")
public class Lesson extends AbstractBaseEntity {

    public enum LessonStatus {
        SCHEDULED, IN_PROGRESS, COMPLETED, CANCELLED
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClass;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30)
    private LessonStatus status;

    @NotBlank
    @Size(max = 1000)
    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sick_exercise")
    private Exercise sickExercise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "self_exercise")
    private Exercise selfExercise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "control_exercise")
    private Exercise controlExercise;

    public Lesson() {
    }

    // геттеры и сеттеры
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public LessonStatus getStatus() {
        return status;
    }

    public void setStatus(LessonStatus status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Exercise getSickExercise() {
        return sickExercise;
    }

    public void setSickExercise(Exercise sickExercise) {
        this.sickExercise = sickExercise;
    }

    public Exercise getSelfExercise() {
        return selfExercise;
    }

    public void setSelfExercise(Exercise selfExercise) {
        this.selfExercise = selfExercise;
    }

    public Exercise getControlExercise() {
        return controlExercise;
    }

    public void setControlExercise(Exercise controlExercise) {
        this.controlExercise = controlExercise;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", date=" + date +
                ", status=" + status +
                ", title='" + title + '\'' +
                '}';
    }
}

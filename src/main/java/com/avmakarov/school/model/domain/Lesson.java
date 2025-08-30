package com.avmakarov.school.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "log_lesson")
public class Lesson extends AbstractBaseEntity {

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

    @NotBlank
    @Size(max = 1000)
    @Column(name = "topic", nullable = false)
    private String topic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sick_exercise")
    private Exercise sickExercise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "self_exercise")
    private Exercise selfExercise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "control_exercise")
    private Exercise controlExercise;

    @OneToMany(mappedBy = "id.lesson", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LessonStudent> students = new ArrayList<>();

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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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

    public List<LessonStudent> getStudents() {
        return students;
    }

    public void setStudents(List<LessonStudent> students) {
        this.students = students;
    }
}

package com.avmakarov.school.model.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "log_lesson_student")
public class LessonStudent {

    @EmbeddedId
    private Id id;
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state;
    @Column(name = "grade")
    private Integer grade;
    @Column(name = "comment")
    private String comment;

    public enum State {
        Present,
        Sick,
        Unknown,
    }

    @Embeddable
    public static class Id implements Serializable {
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "lesson_id")
        private Lesson lesson;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "student_id")
        private Student student;

        public Id(Lesson lesson, Student student) {
            this.lesson = lesson;
            this.student = student;
        }

        public Id() {
        }

        public Lesson getLesson() {
            return lesson;
        }

        public void setLesson(Lesson lesson) {
            this.lesson = lesson;
        }

        public Student getStudent() {
            return student;
        }

        public void setStudent(Student student) {
            this.student = student;
        }
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

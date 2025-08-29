package com.avmakarov.school.model.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "log_student")
public class Student extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClass;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Student() {
    }

    public Student(Person person, SchoolClass schoolClass) {
        this.person = person;
        this.schoolClass = schoolClass;
    }

    // геттеры и сеттеры
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", person=" + (person != null ? person.getFullName() : "null") +
                ", schoolClass=" + (schoolClass != null ? schoolClass.getName() : "null") +
                ", createdAt=" + createdAt +
                '}';
    }
}

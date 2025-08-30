package com.avmakarov.school.model.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "log_student")
public class Student extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClass;

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
}

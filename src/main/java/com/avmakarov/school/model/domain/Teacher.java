package com.avmakarov.school.model.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "log_teacher")
public class Teacher extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    public Teacher() {
    }

    public Teacher(Person person) {
        this.person = person;
    }

    // геттеры и сеттеры
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}

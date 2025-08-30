package com.avmakarov.school.exceptions;

public class NotFoundException extends HandledException {

    public NotFoundException(String message) {
        super(message);
    }

    public static class Class extends NotFoundException {

        public Class(Long id) {
            super("Не найден класс с id " + id);
        }
    }

    public static class Subject extends NotFoundException {

        public Subject(Long id) {
            super("Не найден предмет с id " + id);
        }
    }

    public static class Teacher extends NotFoundException {
        public Teacher(Long oid) {
            super("Не найден учитель с id " + oid);
        }
    }

    public static class Student extends NotFoundException {
        public Student(Long oid) {
            super("Не найден ученик с id " + oid);
        }
    }

    public static class User extends NotFoundException {
        public User(Long oid) {
            super("Не найден пользователь с id " + oid);
        }
    }

    public static class Lesson extends NotFoundException {
        public Lesson(Long oid) {
            super("Не найден урок с id " + oid);
        }
    }
}

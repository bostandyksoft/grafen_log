package com.avmakarov.school.web.app.transport;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ClassTO(Long oid, @NotNull String name, List<StudentTO> students) {

    public record Table(Long oid, String name) {
    }
}

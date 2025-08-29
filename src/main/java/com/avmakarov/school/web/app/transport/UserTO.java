package com.avmakarov.school.web.app.transport;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UserTO(
        Long oid,
        @NotNull String username,
        @NotNull String info,
        @NotNull boolean admin,
        @NotNull boolean active,
        Long teacher,
        List<Long> students
) {

    public record Table(
            Long oid,
            @NotNull String username,
            @NotNull String info,
            @NotNull boolean admin,
            @NotNull boolean active
    ) {}
}

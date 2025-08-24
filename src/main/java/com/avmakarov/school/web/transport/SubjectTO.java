package com.avmakarov.school.web.transport;

import jakarta.validation.constraints.NotNull;

public record SubjectTO(Long oid, @NotNull String title, @NotNull String comment) {
}
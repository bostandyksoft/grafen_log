package com.avmakarov.school.web.app.admin.transport;

import jakarta.validation.constraints.NotNull;

public record SubjectTO(Long oid, @NotNull String title, @NotNull String comment) {
}
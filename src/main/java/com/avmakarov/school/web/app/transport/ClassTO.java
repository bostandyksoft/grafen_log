package com.avmakarov.school.web.app.transport;

import jakarta.validation.constraints.NotNull;

public record ClassTO(Long oid, @NotNull String name) {
}

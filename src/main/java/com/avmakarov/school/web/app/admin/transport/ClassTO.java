package com.avmakarov.school.web.app.admin.transport;

import jakarta.validation.constraints.NotNull;

public record ClassTO(Long oid, @NotNull String name) {
}

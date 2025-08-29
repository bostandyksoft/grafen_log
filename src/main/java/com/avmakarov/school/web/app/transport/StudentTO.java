package com.avmakarov.school.web.app.transport;

import jakarta.validation.constraints.NotNull;

public record StudentTO(Long oid, @NotNull String fullName, Long classId) {
}

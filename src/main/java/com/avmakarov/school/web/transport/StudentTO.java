package com.avmakarov.school.web.transport;

import jakarta.validation.constraints.NotNull;

public record StudentTO(Long oid, @NotNull String fullName, Long classId) {
}

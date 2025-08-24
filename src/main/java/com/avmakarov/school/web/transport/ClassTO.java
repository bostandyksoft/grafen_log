package com.avmakarov.school.web.transport;

import jakarta.validation.constraints.NotNull;

public record ClassTO(Long oid, @NotNull String name) {
}

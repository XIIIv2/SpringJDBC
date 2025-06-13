package icu.xiii.app.dto.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CustomerDtoRequest(Long id, String fullName, String email, String socialSecurityNumber) {
}

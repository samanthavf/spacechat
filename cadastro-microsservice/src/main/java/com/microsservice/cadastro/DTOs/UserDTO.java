package com.microsservice.cadastro.DTOs;

import java.util.UUID;

public record UserDTO(UUID id, String name, String email, String password) {

}

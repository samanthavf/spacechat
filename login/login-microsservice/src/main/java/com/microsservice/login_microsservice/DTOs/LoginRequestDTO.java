package com.microsservice.login_microsservice.DTOs;

import java.util.UUID;

public record LoginRequestDTO(UUID id,String email, String senha, boolean logedIn) {

}

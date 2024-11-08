package com.microsservice.login_microsservice.DTOs;

import jakarta.persistence.Column;

public record LoginRequestDTO(@Column(unique = true) String email, String senha, boolean logedIn) {

}

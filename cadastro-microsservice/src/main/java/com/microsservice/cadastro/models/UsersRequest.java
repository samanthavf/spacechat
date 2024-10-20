package com.microsservice.cadastro.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TABLE_USERS")
public class UsersRequest {
	@Id
	@GeneratedValue(generator = "UUID")
	private UUID id;
	private String name;
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	private String password;
}

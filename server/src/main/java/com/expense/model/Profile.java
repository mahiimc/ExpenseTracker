package com.expense.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "exp_user_profile")
@Entity
@Data
@NoArgsConstructor
public class Profile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long profileId;
	private String firstName;
	private String lastName;
	private String email;
	private String mobileNum;
	private String url;
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
}

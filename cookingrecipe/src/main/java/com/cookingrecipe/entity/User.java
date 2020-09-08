package com.cookingrecipe.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Users")
@Getter
@Setter
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	
	private String fullname;
	
	@NotNull
	@Email
	@Column(nullable = false)
	private String mail;

	@NotNull
	@Column(nullable = false)
	private String password;

	private String image;

	@NotNull
	@Column(nullable = false)
	private Integer role = 1;

	@OneToMany(mappedBy = "user")
	private List<Recipe> recipes;

	@OneToMany(mappedBy = "user")
	private List<Comment> comments;

	@ManyToMany
	@JoinTable(name = "Followers")
	private Set<User> followers;

	@ManyToMany(mappedBy = "followers")
	private Set<User> following;

	@ManyToMany
	@JoinTable(name = "Favorites")
	private List<Recipe> favorites;

}

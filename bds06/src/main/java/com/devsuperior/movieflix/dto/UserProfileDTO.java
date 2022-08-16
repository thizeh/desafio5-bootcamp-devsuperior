package com.devsuperior.movieflix.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.devsuperior.movieflix.entities.User;

public class UserProfileDTO implements Serializable {

	  private static final long serialVersionUID = 1L;

	  
	  private Long id;

	  @NotBlank(message = "Campo nome é obrigratório")
	  private String name;

	  @NotBlank(message = "Campo email é obrigratório")
	  private String email;

	  public UserProfileDTO(){}

	  public UserProfileDTO(Long id, String name, String email) {
	    this.id = id;
	    this.name = name;
	    this.email = email;
	  }

	  public UserProfileDTO(User user) {
	    id = user.getId();
	    name = user.getName();
	    email = user.getEmail();
	  }

	  public Long getId() {
	    return id;
	  }

	  public void setId(Long id) {
	    this.id = id;
	  }

	  public String getName() {
	    return name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }

	  public String getEmail() {
	    return email;
	  }

	  public void setEmail(String email) {
	    this.email = email;
	  }
}

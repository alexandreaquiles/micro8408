package br.com.caelum.eats.dto;

import br.com.caelum.eats.model.User;
import lombok.Data;

@Data
public class UserInfoDto {

	private String username;
	private String password;
	
	public User toUser() {
		return new User(username, password);
	}

}

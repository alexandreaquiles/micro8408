package br.com.caelum.eats.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.dto.AuthenticationDto;
import br.com.caelum.eats.dto.UserInfoDto;
import br.com.caelum.eats.model.Restaurante;
import br.com.caelum.eats.model.Role;
import br.com.caelum.eats.model.User;
import br.com.caelum.eats.repository.RestauranteRepository;
import br.com.caelum.eats.service.JwtTokenManager;
import br.com.caelum.eats.service.UserService;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

	private AuthenticationManager authManager;
	private JwtTokenManager jwtTokenManager;
	private UserService userService;
	private RestauranteRepository restauranteRepo;

	@PostMapping
	public ResponseEntity<AuthenticationDto> authenticate(@RequestBody UserInfoDto login) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				login.getUsername(), login.getPassword());

		try {
			Authentication authentication = authManager.authenticate(authenticationToken);
			User user = (User) authentication.getPrincipal();
			String jwt = jwtTokenManager.generateToken(user);
			Long targetId = getTargetIdFor(user);
			AuthenticationDto tokenResponse = new AuthenticationDto(user, jwt, targetId);
			return ResponseEntity.ok(tokenResponse);
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}

	}
	
	@PostMapping("/parceiro")
	public Long register(@RequestBody UserInfoDto userInfo) {
		User user = userInfo.toUser();
		user.addRole(Role.ROLES.PARCEIRO);
		User salvo = userService.save(user);
		return salvo.getId();
	}

	private Long getTargetIdFor(User user) {
		if(user.isInRole(Role.ROLES.PARCEIRO)) {
			Restaurante restaurante = restauranteRepo.findByUser(user);
			if (restaurante != null) {
				return restaurante.getId();
			}
		}
		return null;
	}

}

package br.com.caelum.eats.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.caelum.eats.model.Restaurante;
import br.com.caelum.eats.model.Role;
import br.com.caelum.eats.model.User;
import br.com.caelum.eats.repository.RestauranteRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthorizationService {

	private RestauranteRepository restauranteRepo;

	public boolean checaTargetId(Authentication authentication, long id) {
		User user = (User) authentication.getPrincipal();
		if(user.isInRole(Role.ROLES.PARCEIRO)) {
			Restaurante restaurante = restauranteRepo.findByUser(user);
			if (restaurante != null) {
				return id == restaurante.getId();
			}
		}
		return false;
	}

}

package br.com.caelum.eats.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.caelum.eats.model.User;
import br.com.caelum.eats.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

	private UserRepository userRepository;
	private BCryptPasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByName(username);
		return user.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
	}

	public UserDetails loadUserById(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		return user.orElseThrow(
				() -> new UsernameNotFoundException("Não foi possível encontrar o usuário com id: " + userId));
	}

	public User save(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

}

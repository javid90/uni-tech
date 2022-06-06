package az.unibank.unitech.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import az.unibank.unitech.dto.request.UserLoginRequestDto;
import az.unibank.unitech.dto.request.UserRegisterRequestDto;
import az.unibank.unitech.dto.response.UserRegisterResponseDto;
import az.unibank.unitech.entity.UserEntity;
import az.unibank.unitech.enumeration.EntityStateEnum;
import az.unibank.unitech.exception.GeneralException;
import az.unibank.unitech.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	private final ModelMapper modelMapper;
	private final UserRepository repo;
	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder passwordEncoder;

	public UserRegisterResponseDto register(UserRegisterRequestDto request) {

		if (repo.findByPin(request.getPin()) != null)
			throw new GeneralException("The pin already exists!");

		UserEntity user = new UserEntity();
		user.setPin(request.getPin());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setStatus(EntityStateEnum.ACTIVE);

		repo.save(user);

		return modelMapper.map(user, UserRegisterResponseDto.class);
	}

	public void login(UserLoginRequestDto request) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getPin(), request.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

	}

}

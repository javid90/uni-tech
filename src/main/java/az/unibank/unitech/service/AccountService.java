package az.unibank.unitech.service;

import java.math.BigDecimal;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import az.unibank.unitech.dto.request.AccountTransferRequestDto;
import az.unibank.unitech.dto.response.AccountResponseDto;
import az.unibank.unitech.entity.AccountEntity;
import az.unibank.unitech.enumeration.EntityStateEnum;
import az.unibank.unitech.exception.GeneralException;
import az.unibank.unitech.repository.AccountRepository;
import az.unibank.unitech.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AccountService {

	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

	private final ModelMapper modelMapper;
	private final UserRepository userRepo;
	private final AccountRepository accountRepo;

	public AccountResponseDto createAccount() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String currentUsername = "";

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			currentUsername = authentication.getName();
		}

		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setAccountNo(String.valueOf((new Random()).nextInt(999999999)));
		accountEntity.setBalance(BigDecimal.ZERO);
		accountEntity.setStatus(EntityStateEnum.ACTIVE);
		accountEntity.setUserId(String.valueOf(userRepo.findByPin(currentUsername).getId()));

		accountRepo.save(accountEntity);

		return modelMapper.map(accountEntity, AccountResponseDto.class);
	}

	public Set<AccountResponseDto> getAccounts() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUsername = authentication.getName();

			return modelMapper.map(userRepo.findByPin(currentUsername).getAccounts().stream().filter((e -> e.getStatus().equals(EntityStateEnum.ACTIVE)))
					.collect(Collectors.toSet()), new TypeToken<Set<AccountResponseDto>>() {}.getType());
		}

		return null;
	}

	@Transactional
	public void transfer(AccountTransferRequestDto request) {
		AccountEntity sourceAccount = accountRepo.findByAccountNo(request.getSourceAccountNo());
		if (sourceAccount != null)
			sourceAccount.setBalance(sourceAccount.getBalance().subtract(request.getAmount()));
		else
			throw new GeneralException("The source account doesn't exist!");

		AccountEntity targetAccount = accountRepo.findByAccountNo(request.getTargetAccountNo());
		if (targetAccount != null)
			targetAccount.setBalance(targetAccount.getBalance().add(request.getAmount()));
		else
			throw new GeneralException("The target account doesn't exist!");

		if (sourceAccount.getAccountNo().equalsIgnoreCase(targetAccount.getAccountNo()))
			throw new GeneralException("You are making transfer to the same account!");
		else if (sourceAccount.getBalance().compareTo(new BigDecimal(0.00)) <= 0)
			throw new GeneralException("There is no enough funds in your balance!");
		else if (targetAccount.getStatus().equals(EntityStateEnum.DEACTIVE))
			throw new GeneralException("The target account is not active!");

		accountRepo.save(sourceAccount);
		accountRepo.save(targetAccount);
	}

}

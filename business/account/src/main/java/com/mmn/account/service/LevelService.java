package com.mmn.account.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mmn.account.exceptions.AlreadyActiveEmailInviteException;
import com.mmn.account.model.entity.Level;
import com.mmn.account.model.type.LevelStatus;
import com.mmn.account.repository.LevelRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LevelService {
	
	private final LevelRepository levelRepository;
	
    public Level validateReferralCode(final String accountId) {
        final Optional<Level> optional = 
        		levelRepository.findByChildId(accountId);
        if (optional.isPresent() && optional.get().isActive()) {
            throw new AlreadyActiveEmailInviteException();
        }
        final Level level = optional.get();
        level.setActiveDate(LocalDate.now());
        level.setStatus(LevelStatus.Active);
        level.setScore(scoreValidateInvite());
        return levelRepository.save(level);
    }

    private Integer scoreValidateInvite() {
    	//TODO pontuacao do account que está validando a subscricao
        return 1;
    }

}

package com.mmn.account.model.dto;

import java.util.List;

import com.mmn.account.model.entity.Account;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LevelTreeDto {

	private final Account parent;
	private final List<Account> childrens;
}

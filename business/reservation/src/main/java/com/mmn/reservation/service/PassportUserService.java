package com.mmn.reservation.service;

import com.mmn.reservation.config.SorProperties;
import com.mmn.reservation.model.ListMembersDto;
import com.mmn.reservation.model.ListMembersResponseDto;
import com.mmn.reservation.model.entity.PassportUser;
import com.mmn.reservation.repository.PassportUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PassportUserService {
    private final PassportUserRepository passportUserRepository;
    private final SorEmailService sorEmailService;
    private final SorClientService sorClientService;
    @Value("${reservation.email.temporary.enabled:false}")
    private boolean sendTempEmailEnabled;

    public List<PassportUser> saveList(final SorProperties.Pack pack, final List<PassportUser> passportUserList) {
        return passportUserList.stream().map(
                pu -> this.save(pack, pu)
        ).filter(Objects::nonNull).collect(Collectors.toList());
    }

    private PassportUser save(final SorProperties.Pack pack, final PassportUser passportUser) {
        final PassportUser savedPassportUser = this.passportUserRepository.save(passportUser);
        if (Objects.nonNull(savedPassportUser)) {
            final String referringUserID = savedPassportUser.getReferringUserID();
            final String referringUserCN = savedPassportUser.getReferringContractNumber();
            if (Objects.isNull(referringUserCN)) {
                log.error("Missing ReferringContractNumber for PassportUser: {}", passportUser);
                return null;
            }
            try {
                final List<ListMembersResponseDto> listMembersResponseDtos = this.sorClientService.listAll(
                        ListMembersDto.builder()
                                .APIUsername(pack.getUsername())
                                .APIPassword(pack.getPassword())
                                .MemberSearchList(Arrays.asList(
                                        ListMembersDto.MemberSearchListDto.builder().ContractNumber(referringUserCN).build()
                                ))
                                .build());
                final ListMembersResponseDto referUser = listMembersResponseDtos.stream()
                        .filter(Objects::nonNull)
                        .peek(member -> log.info("SOR Member: {}", member))
                        .filter(member -> member.getUserID().toString().equals(referringUserID))
                        .findAny()
                        .orElseThrow(() -> new RuntimeException("ReferringUserId not found"));
                if (referUser != null) {
                    this.sorEmailService.sendInvitationEmail(
                            referUser.getOtherID(),
                            passportUser.getEmail(),
                            passportUser.getFirstName());
                    if (sendTempEmailEnabled) {
                        this.sorEmailService.sendTemporaryEmail(
                                passportUser.getEmail()
                        );
                    }
                }
            } catch (IOException io) {
                throw new RuntimeException("Error calling the list members request");
            }
        }
        log.info("SavedPassportUser: {}", savedPassportUser);
        return savedPassportUser;
    }
}

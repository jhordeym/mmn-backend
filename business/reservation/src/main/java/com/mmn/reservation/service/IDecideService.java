package com.mmn.reservation.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mmn.reservation.config.IDecideProperties;
import com.mmn.reservation.model.IDecideAccountCreateDto;
import com.mmn.reservation.model.IDecideAccountCreateResponseDto;
import com.mmn.reservation.model.IDecideAccountResponseDto;
import com.mmn.reservation.model.entity.IDecideAccount;
import com.mmn.reservation.repository.IDecideAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class IDecideService {
    private final IDecideProperties properties;
    private final IDecideAccountRepository repository;
    private final Gson gson = new Gson();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public IDecideAccountCreateResponseDto create(final IDecideAccountCreateDto iDecideAccount) throws IOException, URISyntaxException {
        final String URL = "users/create";
        URIBuilder uriBuilder = new URIBuilder(properties.getUrl() + URL);
        uriBuilder.addParameter("apiUser", properties.getApiUser());
        uriBuilder.addParameter("apiKey", properties.getApiKey());
        final Map<String, String> HEADERS = new HashMap<>();
        HEADERS.put(HttpHeaders.CONTENT_TYPE, "application/json");
        final HttpResponse response = this.genericPost(uriBuilder, iDecideAccount.getIdecideAccount(), HEADERS);
        final IDecideAccountResponseDto iDecideAccountCreateResponseDto = objectMapper.readValue(response.getEntity().getContent(), IDecideAccountResponseDto.class);
        final IDecideAccount save;
        if (Objects.nonNull(iDecideAccountCreateResponseDto.getSuccess())) {
            log.debug("Save iDecide User on BD");
            save = this.save(IDecideAccount.builder()
                    .id(iDecideAccountCreateResponseDto.getUserId())
                    .accountId(iDecideAccount.getTravinedAccountId())
                    .build());
        } else {
            throw new RuntimeException(iDecideAccountCreateResponseDto.getErrors().get(0));
        }
        return IDecideAccountCreateResponseDto
                .builder()
                .IDecideResponse(iDecideAccountCreateResponseDto)
                .IDecideSavedAccount(save)
                .build();
    }

    private HttpResponse genericPost(final URIBuilder url, final Object o, Map<String, String> headers) throws IOException, URISyntaxException {
        final String json = gson.toJson(o);
        final HttpPost request = new HttpPost(url.build());
        request.setEntity(new ByteArrayEntity(json.getBytes("UTF-8")));
        headers.forEach((k, v) -> request.setHeader(k, v));
        return HttpClientBuilder.create().build().execute(request);
    }

    public IDecideAccount save(IDecideAccount iDecideAccount) {
        return this.repository.save(iDecideAccount);
    }
}

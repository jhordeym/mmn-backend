package com.mmn.reservation.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mmn.reservation.client.SorClient;
import com.mmn.reservation.client.SorClientV2;
import com.mmn.reservation.config.SorProperties;
import com.mmn.reservation.model.AccountDto;
import com.mmn.reservation.model.AccountResponseDto;
import com.mmn.reservation.model.FullLoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
@Service
@RequiredArgsConstructor
public class SorService {
    private final SorClient client;
    private final SorClientV2 clientV2;
    private final SorProperties properties;
    private final boolean useFeign = false;
    private final Gson gson = new Gson();

    public String login(final FullLoginDto fullLoginDto) throws IOException {
        if (useFeign) {
            return this.client.login(fullLoginDto).getBody();
        }
        final String json = gson.toJson(fullLoginDto);
        final String URL = properties.getSOR_URL().concat("/clubmembership/getlogintokennovalidation");
        final HttpPost request = new HttpPost(URL);
        request.setEntity(new ByteArrayEntity(json.getBytes("UTF-8")));
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        final HttpEntity entity = httpResponse.getEntity();
        final String login = EntityUtils.toString(entity, Charset.defaultCharset());
        return login;
    }

    public AccountResponseDto create(
            final String username,
            final String password,
            final AccountDto accountDto) throws IOException {
        if (useFeign) {
            return clientV2.create(username, password, accountDto).getBody();
        }
        final String json = gson.toJson(accountDto);
        final String URL = properties.getSOR_URL().concat("/v2/clubmembership/createdefault");
        final HttpPost request = new HttpPost(URL);
        request.setEntity(new ByteArrayEntity(json.getBytes("UTF-8")));
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        request.setHeader("x-saveon-username", username);
        request.setHeader("x-saveon-secret", password);
        final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        final HttpEntity entity = httpResponse.getEntity();
        final ObjectMapper objectMapper = new ObjectMapper();
        final AccountResponseDto accountResponseDto = objectMapper.readValue(entity.getContent(), AccountResponseDto.class);
        return accountResponseDto;
    }
}

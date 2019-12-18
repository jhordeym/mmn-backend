package com.mmn.reservation.client;

import com.mmn.reservation.exception.FeignErrorException;
import com.mmn.reservation.model.FullLoginDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "SorApi",
        url = "${reservation.sor_url}"
)
@Headers({
        "Content-Type: application/json"
})
public interface SorClient {
    @PostMapping("/clubmembership/getlogintokennovalidation")
    String login(@RequestBody FullLoginDto loginDto) throws FeignErrorException;
}

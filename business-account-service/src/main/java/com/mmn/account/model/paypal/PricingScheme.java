
package com.mmn.account.model.paypal;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "fixed_price",
    "status",
    "version",
    "create_time",
    "update_time"
})
@Data
public class PricingScheme {

    @JsonProperty("fixed_price")
    public FixedPrice fixedPrice;
    @JsonProperty("status")
    public String status;
    @JsonProperty("version")
    public Integer version;
    @JsonProperty("create_time")
    public String createTime;
    @JsonProperty("update_time")
    public String updateTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

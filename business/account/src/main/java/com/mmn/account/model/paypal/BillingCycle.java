
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
    "frequency",
    "tenure_type",
    "sequence",
    "total_cycles",
    "pricing_scheme"
})
@Data
public class BillingCycle {

    @JsonProperty("frequency")
    public Frequency frequency;
    @JsonProperty("tenure_type")
    public String tenureType;
    @JsonProperty("sequence")
    public Integer sequence;
    @JsonProperty("total_cycles")
    public Integer totalCycles;
    @JsonProperty("pricing_scheme")
    public PricingScheme pricingScheme;
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

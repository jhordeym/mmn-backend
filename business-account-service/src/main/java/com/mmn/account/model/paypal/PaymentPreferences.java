
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
    "auto_bill_outstanding",
    "setup_fee",
    "setup_fee_failure_action",
    "payment_failure_threshold"
})
@Data
public class PaymentPreferences {

    @JsonProperty("auto_bill_outstanding")
    public Boolean autoBillOutstanding;
    @JsonProperty("setup_fee")
    public SetupFee setupFee;
    @JsonProperty("setup_fee_failure_action")
    public String setupFeeFailureAction;
    @JsonProperty("payment_failure_threshold")
    public Integer paymentFailureThreshold;
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


package com.mmn.account.model.paypal;

import java.util.HashMap;
import java.util.List;
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
    "id",
    "product_id",
    "name",
    "description",
    "status",
    "billing_cycles",
    "payment_preferences",
    "taxes",
    "create_time",
    "update_time",
    "links"
})
@Data
public class PaypalResponse {

    @JsonProperty("id")
    public String id;
    @JsonProperty("product_id")
    public String productId;
    @JsonProperty("name")
    public String name;
    @JsonProperty("description")
    public String description;
    @JsonProperty("status")
    public String status;
    @JsonProperty("billing_cycles")
    public List<BillingCycle> billingCycles = null;
    @JsonProperty("payment_preferences")
    public PaymentPreferences paymentPreferences;
    @JsonProperty("taxes")
    public Taxes taxes;
    @JsonProperty("create_time")
    public String createTime;
    @JsonProperty("update_time")
    public String updateTime;
    @JsonProperty("links")
    public List<Link> links = null;
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

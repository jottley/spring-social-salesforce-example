package net.ottleys.springsocialsalesforceexample;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "connected.app")
public class ConnectedAppConfiguration {

    @NotBlank private String consumerToken;
    @NotBlank private String consumerSecret;
    @NotBlank private String scopes;

    /**
     * @return
     */
    public String getConsumerToken() {
        return this.consumerToken;
    }
    
    /**
     * @param consumerToken
     */
    public void setConsumerToken(String consumerToken) {
        this.consumerToken = consumerToken;
    }

    /**
     * @return
     */
    public String getConsumerSecret() {
        return this.consumerSecret;
    }

    /**
     * @param consumerSecret
     */
    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    /**
     * @return
     */
    public String getScopes() {
        return this.scopes;
    }

    /**
     * @param scopes
     */
    public void setScopes(String scopes)
    {
        this.scopes = scopes;
    }
}
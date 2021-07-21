package net.ottleys.springsocialsalesforceexample;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.salesforce.connect.SalesforceConnectionFactory;

@Configuration
public class SpringSocialSalesforceConfiguration {

    private final ConnectedAppConfiguration connectedAppConfiguration;
    private String instanceUrl; 
    
    @Autowired
    public SpringSocialSalesforceConfiguration(ConnectedAppConfiguration connectedAppConfiguration)
    {
        this.connectedAppConfiguration = connectedAppConfiguration;
    }
    
    /**
     * @return
     */
    public ConnectedAppConfiguration getConnectedAppConfiguration()
    {
        return this.connectedAppConfiguration;
    }

    /**
     * @param instanceUrl the instanceUrl to set
     */
    public void setInstanceUrl(String instanceUrl) {
        this.instanceUrl = instanceUrl;
    }

    
    @Bean
    public SalesforceConnectionFactory salesforceConnectionFactory()
    {
        if (StringUtils.isNotBlank(instanceUrl))
        { 
            return new SalesforceConnectionFactory(connectedAppConfiguration.getConsumerToken(), connectedAppConfiguration.getConsumerSecret());
        }
        else
        {
            return new SalesforceConnectionFactory(connectedAppConfiguration.getConsumerToken(), connectedAppConfiguration.getConsumerSecret(), instanceUrl);
        }
        
    }
}
package net.ottleys.springsocialsalesforceexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.salesforce.api.Salesforce;
import org.springframework.social.salesforce.connect.SalesforceConnectionFactory;
import org.springframework.stereotype.Service;

@Service
public class SpringSocialSalesforceService {
    
    private static final Logger logger = LoggerFactory.getLogger(SpringSocialSalesforceService.class);
    
    @Autowired
    private SalesforceConnectionFactory salesforceConnectionFactory;

    SpringSocialSalesforceConfiguration springSocialSalesforceConfiguration;
    String redirectUri = "http://localhost:8080/completeAuthentication";

    private Connection<Salesforce> connection;

    @Autowired
    public SpringSocialSalesforceService(SpringSocialSalesforceConfiguration springSocialSalesforceConfiguration) {
        this.springSocialSalesforceConfiguration = springSocialSalesforceConfiguration;
    }

    public Connection<Salesforce> getConnection()
    {
        if (connection != null)
        {
            logger.info("#### have current connection");
            String displayname = connection.getDisplayName();
            logger.info("#### display name: " + displayname);
        }
        
        //salesforceConnectionFactory.createConnection(accessGrant);

        return connection;
    }

    public Connection<Salesforce> getConnection(AccessGrant accessGrant)
    {
        connection = salesforceConnectionFactory.createConnection(accessGrant);

        return connection;
    }


    public String getAuthenticationUrl(String state)
    {
        logger.info("Generate Salesforce Authentication URL");
        OAuth2Parameters parameters = new OAuth2Parameters();
        parameters.setRedirectUri(redirectUri);
        parameters.setScope(springSocialSalesforceConfiguration.getConnectedAppConfiguration().getScopes());
        parameters.setState(state);
        return salesforceConnectionFactory.getOAuthOperations().buildAuthenticateUrl(GrantType.AUTHORIZATION_CODE, parameters);
    }


    public AccessGrant completeAuthentication(String accessCode)
    {
        logger.info("Completing Salesforce OAuth Authentication" );
        AccessGrant accessGrant = null;

        // Exchange the accessCode for the access and refresh tokens
        accessGrant = salesforceConnectionFactory.getOAuthOperations().exchangeForAccess(accessCode, redirectUri, null);

        return accessGrant;
    }


    public void test()
    {
        
    }

}
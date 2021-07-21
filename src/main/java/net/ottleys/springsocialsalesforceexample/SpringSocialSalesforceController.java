package net.ottleys.springsocialsalesforceexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.social.connect.Connection;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.salesforce.api.Salesforce;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SpringSocialSalesforceController {

    private static final Logger logger = LoggerFactory.getLogger(SpringSocialSalesforceController.class);
    
    @Autowired
    private SpringSocialSalesforceService springSocialSalesforceService;

    @GetMapping("/")
    public String getIndex(Model model)
    {
        //TODO Is there a current connection for this user? If No, return the authURL so it can be used in a link.  
        //If Yes, get and display user information

        Connection<Salesforce> connection = springSocialSalesforceService.getConnection();

        model.addAttribute("greeting", "hello");
        return "index";
    }
    
    /**
        This is a test method
     */
    @GetMapping("/authenticationUrl")
    public String getAuthenticationUrl(Model model) {
        String authUrl = springSocialSalesforceService.getAuthenticationUrl("state");
        model.addAttribute("authUrl", authUrl);
        return "authUrl";
    }

    @GetMapping(value="/completeAuthentication")
    public String getCompleteAuthentication(@RequestParam String code, Model model) {
        AccessGrant accessGrant = springSocialSalesforceService.completeAuthentication(code);
        //model.addAttribute("accessGrant", accessGrant.getAccessToken());
        Connection<Salesforce> connection = springSocialSalesforceService.getConnection(accessGrant);
        logger.info("#### displayname: " + connection.getDisplayName());

        return "complete";
    }
    

}
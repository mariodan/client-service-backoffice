package ro.enel.backoffice.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@Configuration
public class ServicesConfigProperties {

    @Value("${services.auth-service.url}")
    private String authServiceUrl;

    @Value("${services.auth-service.urlPath}")
    private String authServiceUrlPath;

    @Value("${services.user-profile.url}")
    private String userProfileUrl;

}

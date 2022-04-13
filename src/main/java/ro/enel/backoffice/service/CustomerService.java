package ro.enel.backoffice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.enel.backoffice.config.ServicesConfigProperties;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final RestTemplate restTemplate;
    private final ServicesConfigProperties servicesConfigProperties;

    public Map<String, String> getCustomerDetailsByCif(final String cif) {
        return  restTemplate.getForObject(servicesConfigProperties.getAuthServiceUrl(), Map.class);
    }

}

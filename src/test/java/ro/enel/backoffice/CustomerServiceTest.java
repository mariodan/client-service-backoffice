package ro.enel.backoffice;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ro.enel.backoffice.config.ServicesConfigProperties;
import ro.enel.backoffice.service.CustomerService;

import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    private static final String CIF = "9999";

    @Mock
    private RestTemplate restTemplate;

    @Autowired
    ServicesConfigProperties servicesConfig;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void testCustomerServiceCall() throws Exception {
        Map<String, String> myReturnData = new HashMap<>();
        myReturnData.put("id", CIF);

        Mockito
                .when(restTemplate.getForObject("", Map.class))
                .thenReturn((Map) new ResponseEntity<>(myReturnData, HttpStatus.OK));

        Map<String, String> serviceData = customerService.getCustomerDetailsByCif(CIF);
        Assert.assertEquals(serviceData.get("id"), myReturnData.get("id"));

    }
}

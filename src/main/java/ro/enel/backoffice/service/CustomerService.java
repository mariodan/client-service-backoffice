package ro.enel.backoffice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.enel.backoffice.model.CustomerDetailsResponse;

@Service
public class CustomerService {

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<CustomerDetailsResponse> getCustomerDetailsByCif(String cif) {
        //TODO implement restTemplate call
        return new ResponseEntity<CustomerDetailsResponse>(new CustomerDetailsResponse(), HttpStatus.OK);
    }

}

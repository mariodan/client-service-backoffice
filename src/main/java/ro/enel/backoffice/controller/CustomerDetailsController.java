package ro.enel.backoffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.enel.backoffice.service.CustomerService;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/v1/customerDetails")
public class CustomerDetailsController {


    @Autowired
    CustomerService customerService;

    @GetMapping("/{cif}")
    public ResponseEntity getCustomerDetails(@NotBlank @PathVariable final String cif) {
        return customerService.getCustomerDetailsByCif(cif);
    }
}

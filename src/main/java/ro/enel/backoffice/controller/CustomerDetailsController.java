package ro.enel.backoffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.enel.backoffice.service.CustomerService;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1/customer/info")
public class CustomerDetailsController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/{cif}")
    public Map<String, String> getCustomerDetails(@NotBlank @PathVariable final String cif) {
        return customerService.getCustomerDetailsByCif(cif);
    }
}

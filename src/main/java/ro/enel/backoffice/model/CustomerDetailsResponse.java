package ro.enel.backoffice.model;

import lombok.Data;

@Data
public class CustomerDetailsResponse {

    private String firstName;
    private String lastName;
    private String cif;

}

package hr.xmjosic.xmjbrewery.service;

import hr.xmjosic.xmjbrewery.dto.CustomerDto;
import jdk.jshell.Snippet;

import java.util.UUID;

public interface CustomerService {
  CustomerDto getCustomerById(UUID customerId);

  CustomerDto saveCustomer(CustomerDto customerDto);

  void updateCustomer(UUID customerId, CustomerDto customerDto);

  void deleteCustomer(UUID customerId);
}

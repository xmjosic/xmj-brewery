package hr.xmjosic.xmjbrewery.service.impl;

import hr.xmjosic.xmjbrewery.dto.BeerDto;
import hr.xmjosic.xmjbrewery.dto.CustomerDto;
import hr.xmjosic.xmjbrewery.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
  @Override
  public CustomerDto getCustomerById(UUID customerId) {
    log.debug("--> Get customer by ID: {}", customerId);
    return CustomerDto.builder().id(customerId).name("Pero").build();
  }

  @Override
  public CustomerDto saveCustomer(CustomerDto customerDto) {
    log.debug("--> Save customer: {}", customerDto);
    return CustomerDto.builder().id(UUID.randomUUID()).name("Tvrtko").build();
  }

  @Override
  public void updateCustomer(UUID customerId, CustomerDto customerDto) {
    log.debug("--> Update customer with ID: {}. Customer: {}", customerId, customerDto);
    // TODO
  }

  @Override
  public void deleteCustomer(UUID customerId) {
    log.debug("--> Delete customer by ID: {}", customerId);
    // TODO
  }
}

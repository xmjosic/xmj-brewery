package hr.xmjosic.xmjbrewery.controller;

import hr.xmjosic.xmjbrewery.dto.CustomerDto;
import hr.xmjosic.xmjbrewery.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

  private final CustomerService customerService;

  @GetMapping("/{customerId}")
  public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") UUID customerId) {
    return ResponseEntity.ok(customerService.getCustomerById(customerId));
  }

  @PostMapping
  public ResponseEntity<Void> createCustomer(@RequestBody CustomerDto customerDto) {
    String savedCustomerId = customerService.saveCustomer(customerDto).id().toString();
    return ResponseEntity.created(URI.create("/api/v1/customer/" + savedCustomerId)).build();
  }

  @PutMapping("/{customerId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateBeer(
      @PathVariable("customerId") UUID customerId, @RequestBody CustomerDto customerDto) {
    customerService.updateCustomer(customerId, customerDto);
  }

  @DeleteMapping("/{customerId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteBeer(@PathVariable("customerId") UUID customerId) {
    customerService.deleteCustomer(customerId);
  }
}

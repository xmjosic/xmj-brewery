package hr.xmjosic.xmjbrewery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.xmjosic.xmjbrewery.dto.BeerDto;
import hr.xmjosic.xmjbrewery.dto.CustomerDto;
import hr.xmjosic.xmjbrewery.service.CustomerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static hr.xmjosic.xmjbrewery.dto.BeerStyleEnum.IPA;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SuppressWarnings("unused")
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

  @Autowired private CustomerController customerController;
  @MockBean private CustomerService customerService;
  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;

  private static CustomerDto validCustomer;

  @BeforeAll
  static void setUp() {
    validCustomer = CustomerDto.builder().id(UUID.randomUUID()).name("Hrvoje").build();
  }

  @Test
  void getCustomerById() throws Exception {
    when(customerService.getCustomerById(any(UUID.class))).thenReturn(validCustomer);

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/api/v1/customer/" + validCustomer.id().toString())
                .accept(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(validCustomer.id().toString()))
        .andExpect(jsonPath("$.name").value(validCustomer.name()));
  }

  @Test
  void createCustomer() throws Exception {
    when(customerService.saveCustomer(any(CustomerDto.class))).thenReturn(validCustomer);

    String request = objectMapper.writeValueAsString(validCustomer);

    mockMvc
        .perform(
            post("/api/v1/customer")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(request))
        .andExpect(status().isCreated());
  }

  @Test
  void updateCustomer() throws Exception {
    CustomerDto dto = CustomerDto.builder().name("Boris").build();
    String request = objectMapper.writeValueAsString(dto);

    mockMvc
        .perform(
            put("/api/v1/customer/" + validCustomer.id().toString())
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(request))
        .andExpect(status().isNoContent());
  }

  @Test
  void deleteCustomer() throws Exception {
    mockMvc
        .perform(
            delete("/api/v1/customer/" + validCustomer.id().toString())
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }
}

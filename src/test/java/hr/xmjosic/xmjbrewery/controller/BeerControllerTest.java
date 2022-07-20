package hr.xmjosic.xmjbrewery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.xmjosic.xmjbrewery.dto.BeerDto;
import hr.xmjosic.xmjbrewery.service.BeerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

@SuppressWarnings("unused")
@WebMvcTest(BeerController.class)
class BeerControllerTest {

  @Autowired private BeerController beerController;
  @MockBean private BeerService beerService;
  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;

  private static BeerDto validBeer;

  @BeforeAll
  static void setUp() {
    validBeer =
        BeerDto.builder()
            .id(UUID.randomUUID())
            .beerName("C4")
            .beerStyle("IPA")
            .upc(241567943L)
            .build();
  }

  @Test
  void getBeer() throws Exception {
    Mockito.when(beerService.getBeerById(ArgumentMatchers.any(UUID.class))).thenReturn(validBeer);

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/api/v1/beer/" + validBeer.id().toString())
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(validBeer.id().toString()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.beerName").value(validBeer.beerName()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.beerStyle").value(validBeer.beerStyle()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.upc").value(validBeer.upc()));
  }

  @Test
  void createBeer() throws Exception {
    Mockito.when(beerService.saveBeer(ArgumentMatchers.any(BeerDto.class))).thenReturn(validBeer);

    BeerDto dto = BeerDto.builder().beerName("dollar").beerStyle("despair").build();
    String request = objectMapper.writeValueAsString(dto);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/api/v1/beer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  void updateBeer() throws Exception {
    BeerDto dto = BeerDto.builder().beerName("cliff").beerStyle("excess").build();
    String request = objectMapper.writeValueAsString(dto);

    mockMvc
        .perform(
            MockMvcRequestBuilders.put("/api/v1/beer/" + validBeer.id().toString())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  @Test
  void deleteBeer() throws Exception {
    mockMvc
            .perform(
                    MockMvcRequestBuilders.delete("/api/v1/beer/" + validBeer.id().toString())
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNoContent());
  }
}

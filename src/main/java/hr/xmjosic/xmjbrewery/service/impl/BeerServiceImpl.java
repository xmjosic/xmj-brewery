package hr.xmjosic.xmjbrewery.service.impl;

import hr.xmjosic.xmjbrewery.dto.BeerDto;
import hr.xmjosic.xmjbrewery.service.BeerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
  @Override
  public BeerDto getBeerById(UUID beerId) {
    log.debug("--> Get beer by ID: {}", beerId);
    return BeerDto.builder().id(beerId).beerName("Zmajsko").beerStyle("Pale Ale").build();
  }

  @Override
  public BeerDto saveBeer(BeerDto beerDto) {
    log.debug("--> Save beer: {}", beerDto);
    return BeerDto.builder().id(UUID.randomUUID()).build();
  }

  @Override
  public void updateBeer(UUID beerId, BeerDto beerDto) {
    log.debug("--> Update beer with ID: {}. Beer: {}", beerId, beerDto);
    // TODO
  }

  @Override
  public void deleteBeer(UUID beerId) {
    log.debug("--> Delete beer by ID: {}", beerId);
    // TODO
  }
}

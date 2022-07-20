package hr.xmjosic.xmjbrewery.service;

import hr.xmjosic.xmjbrewery.dto.BeerDto;

import java.util.UUID;

public interface BeerService {
  BeerDto getBeerById(UUID beerId);

  BeerDto saveBeer(BeerDto beerDto);

  void updateBeer(UUID beerId, BeerDto beerDto);

  void deleteBeer(UUID beerId);
}

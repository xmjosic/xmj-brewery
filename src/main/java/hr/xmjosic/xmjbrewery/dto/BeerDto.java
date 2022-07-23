package hr.xmjosic.xmjbrewery.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record BeerDto(UUID id, String beerName, BeerStyleEnum beerStyle, Long upc) {
}

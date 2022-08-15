package hr.xmjosic.xmjbrewery.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.util.UUID;

@Builder
public record BeerDto(UUID id, @NotBlank String beerName, @NotNull BeerStyleEnum beerStyle, @Positive Long upc) {
}

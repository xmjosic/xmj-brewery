package hr.xmjosic.xmjbrewery.controller;

import hr.xmjosic.xmjbrewery.dto.BeerDto;
import hr.xmjosic.xmjbrewery.service.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
  private final BeerService beerService;

  @GetMapping("/{beerId}")
  public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId) {
    return ResponseEntity.ok(beerService.getBeerById(beerId));
  }

  @PostMapping
  public ResponseEntity<Object> createBeer(@Valid @RequestBody BeerDto beerDto) {
    String savedBeerId = beerService.saveBeer(beerDto).id().toString();
    return ResponseEntity.created(URI.create("/api/v1/beer/" + savedBeerId)).build();
  }

  @PutMapping("/{beerId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateBeer(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto) {
    beerService.updateBeer(beerId, beerDto);
  }

  @DeleteMapping("/{beerId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteBeer(@PathVariable("beerId") UUID beerId) {
    beerService.deleteBeer(beerId);
  }
}
